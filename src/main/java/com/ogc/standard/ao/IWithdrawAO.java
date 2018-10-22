package com.ogc.standard.ao;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Withdraw;
import com.ogc.standard.dto.res.XN629903Res;
import com.ogc.standard.spring.ServiceModule;

@ServiceModule
public interface IWithdrawAO {
    String DEFAULT_ORDER_COLUMN = "code";

    // 待申请
    public String applyOrder(String accountNumber, BigDecimal amount,
            String payCardInfo, String payCardNo, String tradePwd,
            String applyUser, String applyUserType, String applyNote);

    // 取现审核
    public void approveOrder(String code, String approveUser,
            String approveResult, String approveNote);

    // 支付回录
    public void payOrder(String code, String payUser, String payResult,
            String payNote, String channelOrder, BigDecimal transFee);

    public void withdrawEnter(String accountNumber, BigDecimal amount,
            String withDate, String channelOrder, String withNote,
            String updater);

    public Paginable<Withdraw> queryWithdrawPage(int start, int limit,
            Withdraw condition);

    public List<Withdraw> queryWithdrawList(Withdraw condition);

    public Withdraw getWithdraw(String code);

    public BigDecimal getTotalWithdraw(String currency);

    public XN629903Res getTotalWithdraw(String applyUser,
            List<String> statusList);
}
