package com.ogc.standard.ao;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Charge;

public interface IChargeAO {

    String DEFAULT_ORDER_COLUMN = "code";

    public Object applyOrderOnline(String userId, String payType,
            BigDecimal transAmount);

    public String applyOrder(String accountNumber, BigDecimal amount,
            String payCardInfo, String payCardNo, String applyUser,
            String applyUserType, String applyNote);

    public void payOrder(String code, String payUser, String payResult,
            String payNote);

    // 手动增发
    public void addSysAccount(BigDecimal amount, String currency,
            String bizNote, String updater);

    public Paginable<Charge> queryChargePage(int start, int limit,
            Charge condition);

    public List<Charge> queryChargeList(Charge condition);

    public Charge getCharge(String code);
}
