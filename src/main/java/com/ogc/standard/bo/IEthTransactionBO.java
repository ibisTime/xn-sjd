package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.web3j.protocol.core.methods.response.Transaction;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.CtqEthTransaction;
import com.ogc.standard.domain.EthTransaction;

public interface IEthTransactionBO extends IPaginableBO<EthTransaction> {

    public int saveEthTransaction(CtqEthTransaction ctqEthTransaction);

    //
    // public List<EthTransaction> queryEthTransactionList(
    // EthTransaction condition);
    //
    public EthTransaction getEthTransaction(String hash);

    //
    // // 估算交易价格

    // 广播
    public String broadcast(String from, String keystoreName,
            String keystoreContent, String keystorePwd, String to,
            BigDecimal value);

    // 获取以太坊gas价格
    public BigDecimal getGasPrice();

    public CtqEthTransaction convertTx(Transaction tx, BigInteger gasUsed,
            BigInteger timestamp);
}
