package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.IAdoptOrderBO;
import com.ogc.standard.bo.IGroupAdoptOrderBO;
import com.ogc.standard.bo.ISettleBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.ISettleDAO;
import com.ogc.standard.domain.AdoptOrder;
import com.ogc.standard.domain.GroupAdoptOrder;
import com.ogc.standard.domain.Settle;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.ESellType;
import com.ogc.standard.enums.ESettleStatus;
import com.ogc.standard.exception.BizException;

@Component
public class SettleBOImpl extends PaginableBOImpl<Settle> implements ISettleBO {

    @Autowired
    private ISettleDAO settleDAO;

    @Autowired
    private IAdoptOrderBO adoptOrderBO;

    @Autowired
    private IGroupAdoptOrderBO groupAdoptOrderBO;

    // todo
    @Override
    public String saveSettle(String userId, String userKind, String refType,
            String refCode, String refNote) {

        Settle settle = new Settle();

        String code = OrderNoGenerater.generate(EGeneratePrefix.Settle
            .getCode());
        settle.setCode(code);
        settle.setUserId(userId);
        settle.setUserKind(userKind);

        BigDecimal orderAmount = BigDecimal.ZERO;// 订单金额

        if (ESellType.COLLECTIVE.getCode().equals(refType)) {
            // 集体订单
            GroupAdoptOrder groupAdoptOrder = groupAdoptOrderBO
                .getGroupAdoptOrder(refCode);
            orderAmount = groupAdoptOrder.getAmount();
        } else {
            // 个人/定向/捐赠订单
            AdoptOrder adoptOrder = adoptOrderBO.getAdoptOrder(refCode);
            // orderAmount = adoptOrder.getAmount();
        }

        // Long rate = 0L; // 结算比例

        // Long amount = rate * orderAmount;// 结算金额

        // settle.setRate(rate);
        // settle.setAmount(amount);
        settle.setStatus(ESettleStatus.TO_SETTLE.getCode());
        settle.setRefType(refType);
        settle.setRefCode(refCode);

        settle.setRefNote(refNote);
        settle.setCreateDatetime(new Date());
        settleDAO.insert(settle);
        return code;
    }

    @Override
    public void refreshStatusByRef(String refCode, String status,
            String handleNote) {

        Settle settle = new Settle();
        settle.setRefCode(refCode);
        settle.setStatus(status);
        settle.setHandleNote(handleNote);
        settle.setHandleDatetime(new Date());

        settleDAO.updateStatusByRef(settle);
    }

    @Override
    public List<Settle> querySettleList(Settle condition) {
        return settleDAO.selectList(condition);
    }

    @Override
    public Settle getSettle(String code) {
        Settle data = null;
        if (StringUtils.isNotBlank(code)) {
            Settle condition = new Settle();
            condition.setCode(code);
            data = settleDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "结算订单不存在！");
            }
        }
        return data;
    }

}
