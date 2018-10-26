package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.IAdoptOrderTreeAO;
import com.ogc.standard.bo.IAdoptOrderTreeBO;
import com.ogc.standard.bo.IBizLogBO;
import com.ogc.standard.bo.ICarbonBubbleOrderBO;
import com.ogc.standard.bo.IGiveTreeRecordBO;
import com.ogc.standard.bo.ISYSConfigBO;
import com.ogc.standard.bo.IToolUseRecordBO;
import com.ogc.standard.bo.ITreeBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.IVisitorBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.AmountUtil;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.SysConstants;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.domain.ToolUseRecord;
import com.ogc.standard.domain.Tree;
import com.ogc.standard.domain.User;
import com.ogc.standard.domain.Visitor;
import com.ogc.standard.dto.res.XN629904Res;
import com.ogc.standard.enums.EAdoptOrderTreeStatus;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.ESysConfigType;
import com.ogc.standard.enums.EToolType;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

@Service
public class AdoptOrderTreeAOImpl implements IAdoptOrderTreeAO {
    static final Logger logger = LoggerFactory
        .getLogger(AdoptOrderAOImpl.class);

    @Autowired
    private IAdoptOrderTreeBO adoptOrderTreeBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IGiveTreeRecordBO giveTreeRecordBO;

    @Autowired
    private ICarbonBubbleOrderBO carbonBubbleOrderBO;

    @Autowired
    private IBizLogBO bizLogBO;

    @Autowired
    private IVisitorBO visitorBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IToolUseRecordBO toolUseRecordBO;

    @Autowired
    private ITreeBO treeBO;

    @Override
    @Transactional
    public void giveTree(String code, String userId, String toMobile) {
        User user = userBO.getUser(userId);
        User toUser = userBO.getUserByMobile(toMobile);

        // 终止现有认养权，产生新的认养权，更改认养权持有人
        AdoptOrderTree data = adoptOrderTreeBO.getAdoptOrderTree(code);
        if (!EAdoptOrderTreeStatus.ADOPT.getCode().equals(data.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "认养权不在认养中");
        }
        adoptOrderTreeBO.giveTree(data, user, toUser);

        // 新增赠送树记录
        giveTreeRecordBO.saveGiveTreeRecord(userId, toUser.getUserId(),
            data.getCode());
    }

    @Override
    public long leaveMessage(String code, String note, String userId) {
        return bizLogBO.leaveMessage(code, note, userId);
    }

    @Override
    public String visit(String code, String userId) {
        // 查询前10条是否有此来访人，如果存在则删除
        Visitor visitor = visitorBO.getTopTenVisitor(code, userId);
        if (null != visitor) {
            visitorBO.removeVisitor(visitor.getCode());
        }

        return visitorBO.saveVisitor(code, userId);
    }

    @Override
    public Paginable<AdoptOrderTree> queryAdoptOrderTreePage(int start,
            int limit, AdoptOrderTree condition) {
        Paginable<AdoptOrderTree> page = adoptOrderTreeBO.getPaginable(start,
            limit, condition);

        if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
            for (AdoptOrderTree adoptOrderTree : page.getList()) {
                initAdoptOrderTree(adoptOrderTree);
            }
        }

        return page;
    }

    @Override
    public List<AdoptOrderTree> queryAdoptOrderTreeList(
            AdoptOrderTree condition) {
        List<AdoptOrderTree> list = adoptOrderTreeBO
            .queryAdoptOrderTreeList(condition);

        if (CollectionUtils.isNotEmpty(list)) {
            for (AdoptOrderTree adoptOrderTree : list) {
                initAdoptOrderTree(adoptOrderTree);
            }
        }

        return list;
    }

    @Override
    public AdoptOrderTree getAdoptOrderTree(String code) {
        AdoptOrderTree data = adoptOrderTreeBO.getAdoptOrderTree(code);
        initAdoptOrderTree(data);
        return data;
    }

    @Override
    public XN629904Res getTotalAmount(String ownerId, List<String> statusList) {
        // 已认养的总值
        BigDecimal totalAmount = adoptOrderTreeBO.getTotalAmount(ownerId,
            statusList);

        // 已认养的树木数量
        long treeCount = adoptOrderTreeBO.getCountByOwner(ownerId, null, null);

        return new XN629904Res(totalAmount, treeCount);
    }

    public void doDailyAdoptOrderTree() {
        logger.info("***************开始生成碳泡泡***************");
        Map<String, String> configMap = sysConfigBO
            .getConfigsMap(ESysConfigType.TPP_RULE.getCode());
        Double rate = Double
            .valueOf(configMap.get(SysConstants.CREATE_TPP_RATE));
        Integer expireHours = Integer
            .valueOf(configMap.get(SysConstants.TPP_EXPIRE_HOUR));

        Date createDatetime = DateUtil.getTodayStart();// 创建时间
        Date invalidDatetime = DateUtil.getRelativeDateOfHour(createDatetime,
            expireHours);// 过期时间

        AdoptOrderTree condition = new AdoptOrderTree();
        condition.setStatus(EAdoptOrderTreeStatus.ADOPT.getCode());

        Integer start = 0;
        Integer limit = 10;

        while (true) {
            Paginable<AdoptOrderTree> page = adoptOrderTreeBO
                .getPaginable(start, limit, condition);

            if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
                // 按订单比例产生碳泡泡
                for (AdoptOrderTree adoptOrderTree : page.getList()) {
                    BigDecimal quantity = AmountUtil
                        .mul(adoptOrderTree.getAmount(), rate);
                    carbonBubbleOrderBO.saveCarbonBubbleOrder(
                        adoptOrderTree.getCode(), createDatetime,
                        invalidDatetime, adoptOrderTree.getCurrentHolder(),
                        quantity);
                }
            } else {
                break;
            }

            start = start + 1;
        }

        logger.info("***************结束生成碳泡泡***************");

    }

    private void initAdoptOrderTree(AdoptOrderTree data) {
        // 树木信息
        Tree tree = treeBO.getTreeByTreeNumber(data.getTreeNumber());
        data.setTree(tree);

        // 当前持有人信息
        User user = userBO.getUserUnCheck(data.getCurrentHolder());
        if (null != user) {
            data.setUser(user);
        }

        // 是否使用保护罩
        List<ToolUseRecord> shelterList = toolUseRecordBO
            .queryTreeToolRecordList(data.getCode(),
                EToolType.SHIELD.getCode());
        if (CollectionUtils.isNotEmpty(shelterList)) {
            data.setIsShelter(EBoolean.YES.getCode());
        } else {
            data.setIsShelter(EBoolean.NO.getCode());
        }

    }

}
