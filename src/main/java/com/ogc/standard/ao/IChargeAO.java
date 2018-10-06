package com.ogc.standard.ao;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Charge;
import com.ogc.standard.dto.res.XN802347Res;

public interface IChargeAO {

    String DEFAULT_ORDER_COLUMN = "code";

    public String applyOrder(String accountNumber, BigDecimal amount,
            String payCardInfo, String payCardNo, String applyUser,
            String applyNote);

    public void payOrder(String code, String payUser, String payResult,
            String payNote);

    public void addSysJf(BigDecimal amount, String bizNote, String updater);

    public Paginable<Charge> queryChargePage(int start, int limit,
            Charge condition);

    public List<Charge> queryChargeList(Charge condition);

    public Charge getCharge(String code);

    // 充币对账详情
    public XN802347Res getChargeCheckInfo(String code);

}
