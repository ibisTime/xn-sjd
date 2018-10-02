package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ogc.standard.ao.ICarbonBubbleOrderAO;
import com.ogc.standard.bo.IAccountBO;
import com.ogc.standard.bo.ICarbonBubbleOrderBO;
import com.ogc.standard.bo.IUserBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.CarbonBubbleOrder;
import com.ogc.standard.domain.User;
import com.ogc.standard.enums.ECarbonBubbleOrderStatus;
import com.ogc.standard.enums.ECurrency;
import com.ogc.standard.exception.BizException;

@Service
public class CarbonBubbleOrderAOImpl implements ICarbonBubbleOrderAO {

    @Autowired
    private ICarbonBubbleOrderBO carbonBubbleOrderBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IAccountBO accountBO;

    @Override
    @Transactional
    public void takeCarbonBubble(String code, String collector) {
        CarbonBubbleOrder data = carbonBubbleOrderBO.getCarbonBubbleOrder(code);
        if (ECarbonBubbleOrderStatus.TAKED.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "碳泡泡已被收取，不能重复收取");
        }
        if (ECarbonBubbleOrderStatus.INVALID.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "碳泡泡已过期，不能收取");
        }
        // 更改碳泡泡产生订单状态
        carbonBubbleOrderBO.takeCarbonBubble(code, collector);
        // 收取人碳泡泡账户加上碳泡泡
        Account tppAccount = accountBO.getAccountByUser(collector,
            ECurrency.TPP.getCode());
        accountBO.changeAmount(tppAccount, new BigDecimal(data.getQuantity()),
            null, null, data.getCode(), null, null);// TODO 流水业务类型枚举
    }

    @Override
    public String addCarbonBubbleOrder(CarbonBubbleOrder data) {
        return carbonBubbleOrderBO.saveCarbonBubbleOrder(data);
    }

    @Override
    public int editCarbonBubbleOrder(CarbonBubbleOrder data) {
        if (!carbonBubbleOrderBO.isCarbonBubbleOrderExist(data.getCode())) {
            throw new BizException("xn0000", "碳泡泡产生订单不存在");
        }
        return carbonBubbleOrderBO.refreshCarbonBubbleOrder(data);
    }

    @Override
    public int dropCarbonBubbleOrder(String code) {
        if (!carbonBubbleOrderBO.isCarbonBubbleOrderExist(code)) {
            throw new BizException("xn0000", "碳泡泡产生订单不存在");
        }
        return carbonBubbleOrderBO.removeCarbonBubbleOrder(code);
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

    public void init(CarbonBubbleOrder data) {
        if (StringUtils.isNotBlank(data.getTaker())) {
            User user = userBO.getUser(data.getTaker());
            data.setTakerNickname(user.getNickname());
            data.setTakerPhoto(user.getPhoto());
        }
    }
}