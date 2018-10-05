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

import com.ogc.standard.ao.IAdoptOrderTreeAO;
import com.ogc.standard.bo.IAdoptOrderTreeBO;
import com.ogc.standard.bo.IBizLogBO;
import com.ogc.standard.bo.ICarbonBubbleOrderBO;
import com.ogc.standard.bo.IGiveTreeRecordBO;
import com.ogc.standard.bo.ISYSConfigBO;
import com.ogc.standard.bo.ITreeBO;
import com.ogc.standard.bo.IVisitorBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.AmountUtil;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.SysConstants;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.domain.GiveTreeRecord;
import com.ogc.standard.domain.Tree;
import com.ogc.standard.domain.Visitor;
import com.ogc.standard.enums.EAdoptOrderTreeStatus;
import com.ogc.standard.enums.ESysConfigType;

@Service
public class AdoptOrderTreeAOImpl implements IAdoptOrderTreeAO {
    static final Logger logger = LoggerFactory
        .getLogger(AdoptOrderAOImpl.class);

    @Autowired
    private IAdoptOrderTreeBO adoptOrderTreeBO;

    @Autowired
    private ITreeBO treeBO;

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

    public void doDailyAdoptOrderTree() {
        logger.info("***************开始生成碳泡泡***************");
        Map<String, String> configMap = sysConfigBO
            .getConfigsMap(ESysConfigType.CREATE_TPP.getCode());
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

            start = start + limit;
        }

        logger.info("***************结束生成碳泡泡***************");

    }

    @Override
    public void giveTree(String code, String toUserId, String userId) {
        // 更改认养权持有人
        AdoptOrderTree data = adoptOrderTreeBO.getAdoptOrderTree(code);
        data.setCurrentHolder(toUserId);
        adoptOrderTreeBO.giveTree(data);
        // 新增赠送树记录
        GiveTreeRecord record = new GiveTreeRecord();
        record.setAdoptTreeCode(code);
        record.setUserId(userId);
        record.setToUserId(toUserId);
        record.setCreateDatetime(new Date());
        giveTreeRecordBO.saveGiveTreeRecord(record);
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
        for (AdoptOrderTree adoptOrderTree : page.getList()) {
            initAdoptOrderTree(adoptOrderTree);
        }
        return page;
    }

    @Override
    public List<AdoptOrderTree> queryAdoptOrderTreeList(
            AdoptOrderTree condition) {
        List<AdoptOrderTree> list = adoptOrderTreeBO
            .queryAdoptOrderTreeList(condition);
        for (AdoptOrderTree adoptOrderTree : list) {
            initAdoptOrderTree(adoptOrderTree);
        }
        return list;
    }

    @Override
    public AdoptOrderTree getAdoptOrderTree(String code) {
        AdoptOrderTree data = adoptOrderTreeBO.getAdoptOrderTree(code);
        initAdoptOrderTree(data);
        return data;
    }

    private void initAdoptOrderTree(AdoptOrderTree data) {
        Tree tree = treeBO.getTreeByTreeNumber(data.getTreeNumber());
        data.setTree(tree);
    }

}
