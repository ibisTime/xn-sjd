package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.ICarbonBubbleOrderAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.IAdoptOrderTreeBO;
import com.ogc.standard.bo.IBizLogBO;
import com.ogc.standard.bo.ICarbonBubbleOrderBO;
import com.ogc.standard.bo.ISYSConfigBO;
import com.ogc.standard.bo.IToolUseRecordBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.SysConstants;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.domain.CarbonBubbleOrder;
import com.ogc.standard.domain.User;
import com.ogc.standard.dto.res.XN629350Res;
import com.ogc.standard.enums.EBizLogType;
import com.ogc.standard.enums.ECarbonBubbleOrderStatus;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.enums.EJourBizTypePlat;
import com.ogc.standard.enums.EJourBizTypeUser;
import com.ogc.standard.enums.ESysConfigType;
import com.ogc.standard.enums.ESystemAccount;
import com.ogc.standard.enums.EToolType;
import com.ogc.standard.exception.BizException;

@Service
public class CarbonBubbleOrderAOImpl implements ICarbonBubbleOrderAO {
    static final Logger logger = LoggerFactory
        .getLogger(AdoptOrderAOImpl.class);

    @Autowired
    private ICarbonBubbleOrderBO carbonBubbleOrderBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IBizLogBO bizLogBO;

    @Autowired
    private IAdoptOrderTreeBO adoptOrderTreeBO;

    @Autowired
    private IToolUseRecordBO toolUseRecordBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    public void expireCarbonBubble() {
        logger.info("***************开始扫描已过期碳泡泡***************");
        CarbonBubbleOrder condition = new CarbonBubbleOrder();
        condition.setStatus(ECarbonBubbleOrderStatus.TO_TAKE.getCode());
        condition.setInvalidDatetimeEnd(DateUtil.getHourStart());

        Integer start = 1;
        Integer limit = 10;

        while (true) {
            Paginable<CarbonBubbleOrder> page = carbonBubbleOrderBO
                .getPaginable(start, limit, condition);

            if (null != page && CollectionUtils.isNotEmpty(page.getList())) {
                for (CarbonBubbleOrder carbonBubbleOrder : page.getList()) {
                    carbonBubbleOrderBO
                        .expireCarbonBubbleOrder(carbonBubbleOrder.getCode());
                }
            } else {
                break;
            }

            start = start + 1;
        }

        logger.info("***************结束扫描已过期碳泡泡***************");
    }

    @Override
    @Transactional
    public XN629350Res takeCarbonBubble(String code, String collector) {
        CarbonBubbleOrder data = carbonBubbleOrderBO.getCarbonBubbleOrder(code);
        if (ECarbonBubbleOrderStatus.TAKED.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "碳泡泡已被收取，不能重复收取");
        }
        if (ECarbonBubbleOrderStatus.INVALID.getCode()
            .equals(data.getStatus())) {
            throw new BizException("xn0000", "碳泡泡已过期，不能收取");
        }

        // 正在使用的道具
        if (CollectionUtils.isNotEmpty(toolUseRecordBO.queryTreeToolRecordList(
            data.getAdoptTreeCode(), EToolType.SHIELD.getCode()))) {
            if (!data.getAdoptUserId().equals(collector)) {
                // 添加日志
                bizLogBO.gatherNoCarbonBubble(data.getAdoptTreeCode(),
                    data.getAdoptUserId(), collector);

                return new XN629350Res("1", "正在使用保护罩，碳泡泡不能收取");
            }
        }

        // 每日被收取上限
        if (!data.getAdoptUserId().equals(collector)) {
            BigDecimal otherTakedQuantity = carbonBubbleOrderBO
                .otherTakedTppAmount(data.getAdoptUserId());

            Map<String, String> configMap = sysConfigBO
                .getConfigsMap(ESysConfigType.TPP_RULE.getCode());
            BigDecimal otherTakeMaxQuantity = new BigDecimal(
                configMap.get(SysConstants.OTHER_TAKE_MAX_QUANTITY));
            otherTakeMaxQuantity = otherTakeMaxQuantity
                .multiply(new BigDecimal(1000));// 每天最多被偷取数量

            if (otherTakedQuantity.compareTo(otherTakeMaxQuantity) != -1) {
                throw new BizException("xn0000", "用户今天被收取碳泡泡数量已达上限，不能收取");
            }

        }

        // 更改碳泡泡产生订单状态
        carbonBubbleOrderBO.takeCarbonBubble(code, collector);

        // 收取人碳泡泡账户加上碳泡泡
        Account sysTppAccount = accountBO
            .getAccount(ESystemAccount.SYS_ACOUNT_TPP_POOL.getCode());
        Account userTppAccount = accountBO.getAccountByUser(collector,
            ECurrency.TPP.getCode());

        BigDecimal quantity = data.getQuantity();
        // if (quantity.compareTo(sysTppAccount.getAmount()) == 1) {
        // quantity = sysTppAccount.getAmount();
        // }

        accountBO.transAmount(sysTppAccount, userTppAccount, quantity,
            EJourBizTypeUser.ADOPT_DAY_BACK.getCode(),
            EJourBizTypePlat.ADOPT_DAY_BACK.getCode(),
            EJourBizTypeUser.ADOPT_DAY_BACK.getValue(),
            EJourBizTypePlat.ADOPT_DAY_BACK.getValue(), data.getCode());

        // 添加日志
        AdoptOrderTree adoptOrderTree = adoptOrderTreeBO
            .getAdoptOrderTree(data.getAdoptTreeCode());
        bizLogBO.gatherCarbonBubble(adoptOrderTree.getCode(),
            adoptOrderTree.getCurrentHolder(), data.getQuantity(), collector,
            EBizLogType.GATHER.getCode());

        return new XN629350Res("0", "收取成功");
    }

    @Override
    @Transactional
    public BigDecimal takeCarbonBubbleByAdopt(String adoptTreeCode,
            String collector) {
        // 正在使用的道具
        if (CollectionUtils.isNotEmpty(toolUseRecordBO.queryTreeToolRecordList(
            adoptTreeCode, EToolType.SHIELD.getCode()))) {
            AdoptOrderTree adoptOrderTree = adoptOrderTreeBO
                .getAdoptOrderTree(adoptTreeCode);

            if (!adoptOrderTree.getCurrentHolder().equals(collector)) {
                throw new BizException("xn0000", "正在使用保护罩，碳泡泡不能收取");
            }
        }

        // 收取所有碳泡泡
        BigDecimal allQuantity = BigDecimal.ZERO;
        List<CarbonBubbleOrder> carbonBubbleOrderList = carbonBubbleOrderBO
            .queryCarbonBubbleOrderListByAdopt(adoptTreeCode);
        if (CollectionUtils.isNotEmpty(carbonBubbleOrderList)) {
            for (CarbonBubbleOrder carbonBubbleOrder : carbonBubbleOrderList) {
                carbonBubbleOrderBO
                    .takeCarbonBubble(carbonBubbleOrder.getCode(), collector);

                // 收取人碳泡泡账户加上碳泡泡
                Account sysTppAccount = accountBO
                    .getAccount(ESystemAccount.SYS_ACOUNT_TPP_POOL.getCode());
                Account userTppAccount = accountBO.getAccountByUser(collector,
                    ECurrency.TPP.getCode());

                BigDecimal quantity = carbonBubbleOrder.getQuantity();
                // if (quantity.compareTo(sysTppAccount.getAmount()) == 1) {
                // quantity = sysTppAccount.getAmount();
                // }

                accountBO.transAmount(sysTppAccount, userTppAccount, quantity,
                    EJourBizTypeUser.ADOPT_DAY_BACK.getCode(),
                    EJourBizTypePlat.ADOPT_DAY_BACK.getCode(),
                    EJourBizTypeUser.ADOPT_DAY_BACK.getValue(),
                    EJourBizTypePlat.ADOPT_DAY_BACK.getValue(),
                    carbonBubbleOrder.getCode());

                allQuantity = allQuantity.add(quantity);
            }
        }

        return allQuantity;
    }

    @Override
    public Paginable<CarbonBubbleOrder> queryCarbonBubbleOrderPage(int start,
            int limit, CarbonBubbleOrder condition) {
        Paginable<CarbonBubbleOrder> paginable = carbonBubbleOrderBO
            .getPaginable(start, limit, condition);
        List<CarbonBubbleOrder> list = paginable.getList();
        for (CarbonBubbleOrder data : list) {
            init(data);
        }
        return paginable;
    }

    @Override
    public List<CarbonBubbleOrder> queryCarbonBubbleOrderList(
            CarbonBubbleOrder condition) {
        List<CarbonBubbleOrder> list = carbonBubbleOrderBO
            .queryCarbonBubbleOrderList(condition);
        for (CarbonBubbleOrder data : list) {
            init(data);
        }
        return list;
    }

    @Override
    public CarbonBubbleOrder getCarbonBubbleOrder(String code) {
        CarbonBubbleOrder data = carbonBubbleOrderBO.getCarbonBubbleOrder(code);
        init(data);
        return data;
    }

    private void init(CarbonBubbleOrder data) {
        if (StringUtils.isNotBlank(data.getTaker())) {
            User takeUser = userBO.getUser(data.getTaker());
            data.setTakeUser(takeUser);
        }
    }

}
