package com.ogc.standard.bo;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.EthTransaction;

public interface IEthTransactionBO extends IPaginableBO<EthTransaction> {

    // public int saveEthTransaction(CtqEthTransaction ctqEthTransaction,
    // String refNo);
    //
    // public List<EthTransaction> queryEthTransactionList(
    // EthTransaction condition);
    //
    // public EthTransaction getEthTransaction(String hash);
    //
    // // 估算交易价格
    //
    // // 广播
    // public String broadcast(String from, EthXAddress fromSecret, String to,
    // BigDecimal value);
    //
    // // 获取以太坊gas价格
    // public BigDecimal getGasPrice();
}
