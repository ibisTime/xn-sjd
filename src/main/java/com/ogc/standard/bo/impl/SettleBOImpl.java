package com.ogc.standard.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ogc.standard.bo.ISettleBO;
import com.ogc.standard.bo.base.PaginableBOImpl;
import com.ogc.standard.core.OrderNoGenerater;
import com.ogc.standard.dao.ISettleDAO;
import com.ogc.standard.domain.AgentUser;
import com.ogc.standard.domain.Settle;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.enums.EGeneratePrefix;
import com.ogc.standard.enums.ESettleStatus;
import com.ogc.standard.exception.BizException;

@Component
public class SettleBOImpl extends PaginableBOImpl<Settle> implements ISettleBO {

    @Autowired
    private ISettleDAO settleDAO;

    @Override
    public String saveSettle(AgentUser user, BigDecimal settleAmount,
            BigDecimal settleRate, String refCode, String refType,
            String refNote) {
        Settle settle = new Settle();
        String code = OrderNoGenerater
            .generate(EGeneratePrefix.Settle.getCode());
        settle.setCode(code);
        settle.setUserId(user.getUserId());
        settle.setUserKind(user.getLevel());
        settle.setAmount(settleAmount);
        settle.setRate(settleRate);
        settle.setStatus(ESettleStatus.TO_SETTLE.getCode());
        settle.setRefCode(refCode);
        settle.setRefType(refType);

        settle.setRefNote(refNote);
        settle.setCreateDatetime(new Date());
        settleDAO.insert(settle);
        return code;
    }

    @Override
    public void refreshStatusByRefCode(String refCode, String approveResult,
            String handler, String handleNote) {
        Settle settle = new Settle();
        settle.setRefCode(refCode);
        String status = null;
        if (EBoolean.YES.getCode().equals(approveResult)) {
            status = ESettleStatus.SETTLE_YES.getCode();
        } else {
            status = ESettleStatus.SETTLE_NO.getCode();
        }
        settle.setStatus(status);
        settle.setHandleDatetime(new Date());
        settle.setHandleNote(handleNote);
        settleDAO.updateStatusByRefCode(settle);
    }

    @Override
    public List<Settle> querySettleList(Settle condition) {
        return settleDAO.selectList(condition);
    }

    @Override
    public List<Settle> querySettleList(String refCode) {
        Settle condition = new Settle();
        condition.setRefCode(refCode);
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
