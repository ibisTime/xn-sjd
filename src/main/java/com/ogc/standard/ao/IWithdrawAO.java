package com.ogc.standard.ao;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Withdraw;
import com.ogc.standard.dto.res.XN802356Res;
import com.ogc.standard.spring.ServiceModule;

@ServiceModule
public interface IWithdrawAO {
    String DEFAULT_ORDER_COLUMN = "code";

    // 待申请
    public String applyOrder(String accountNumber, BigDecimal amount,
            String payCardInfo, String payCardNo, String tradePwd,
            String applyUser, String applyNote);

    // 取现审核
    public void approveOrder(String code, String approveUser,
            String approveResult, String approveNote);

    // 取现广播
    public void broadcast(String code, String mAddressCode, String approveUser);

    // 打回取现订单
    public void returnOrder(String code, String payUser, String payNote);

    public void payOrder(String code, String payUser, String payResult,
            String payNote, String channelOrder);

    public Paginable<Withdraw> queryWithdrawPage(int start, int limit,
            Withdraw condition);

    public List<Withdraw> queryWithdrawList(Withdraw condition);

    public Withdraw getWithdraw(String code);

    public XN802356Res getWithdrawCheckInfo(String code);

    public BigDecimal getTotalWithdraw(String currency);
}
