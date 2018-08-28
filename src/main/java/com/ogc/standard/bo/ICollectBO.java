package com.ogc.standard.bo;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Collect;

public interface ICollectBO extends IPaginableBO<Collect> {

    // public String saveCollect(String symbol, String from, String to,
    // BigDecimal value, String txHash, String refNo);
    //
    // public List<Collect> queryCollectList(Collect condition);
    //
    // public Collect getCollect(String code);
    //
    // public Collect getCollectByTxHash(String txHash);
    //
    public Collect getCollectByRefNo(String refNo);
    //
    // public void doETHCollect(EthXAddress ethAddress, String chargeCode);
    //
    // public int colectionNoticeETH(Collect data, BigDecimal txfee,
    // Date ethDatetime);
    //
    // public int colectionNoticeBTC(Collect data, BigDecimal txfee,
    // Date btcDatetime);
    //
    // public int colectionNoticeSC(Collect data, String fromAddress,
    // BigDecimal txfee, Date ethDatetime);
    //
    // public int colectionNoticeWAN(Collect data, BigDecimal txfee,
    // Date wanDatetime);
    //
    // // 归集地址使用次数及归集总额查询
    // public EthWAddress getAddressUseInfo(String toAddress, String currency);
    //
    // public BigDecimal getTotalCollect(String currency);

}
