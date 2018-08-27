package com.ogc.standard.bo;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Withdraw;

public interface IWithdrawBO extends IPaginableBO<Withdraw> {

    // public void doCheckTimes(Account account);
    //
    // String applyOrder(Account account, BigDecimal amount, BigDecimal fee,
    // String payCardInfo, String payCardNo, String applyUser,
    // String applyNote);
    //
    // void approveOrder(Withdraw data, EWithdrawStatus status, String
    // approveUser,
    // String approveNote);
    //
    // void payOrder(Withdraw data, EWithdrawStatus status, String payUser,
    // String payNote, String channelOrder, String payCode,
    // BigDecimal payFee);
    //
    // void broadcastOrder(Withdraw data, String txHash, String updater);
    //
    // List<Withdraw> queryWithdrawList(Withdraw condition);
    //
    // Withdraw getWithdraw(String code);
    //
    // public Withdraw getWithdrawByChannelOrder(String hash);
    //
    // public EthMAddress getAddressUseInfo(String fromAddress, String
    // currency);
    //
    // public BigDecimal getTotalWithdraw(String currency);
}
