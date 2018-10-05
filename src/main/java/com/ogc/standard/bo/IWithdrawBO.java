package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Account;
import com.ogc.standard.domain.Withdraw;
import com.ogc.standard.enums.EWithdrawStatus;

public interface IWithdrawBO extends IPaginableBO<Withdraw> {

    String applyOrder(Account account, BigDecimal amount, BigDecimal fee,
            String payCardInfo, String payCardNo, String applyUser,
            String applyNote);

    BigDecimal doCheckAndGetFee(Account account, BigDecimal amount);

    void approveOrder(Withdraw data, EWithdrawStatus status,
            String approveUser, String approveNote);

    void payOrder(Withdraw data, EWithdrawStatus status, String payUser,
            String payNote, String channelOrder, BigDecimal payFee);

    List<Withdraw> queryWithdrawList(Withdraw condition);

    Withdraw getWithdraw(String code);

    public Withdraw getWithdrawByChannelOrder(String hash);

    public BigDecimal getTotalWithdraw(String currency);
}
