package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cdkj.coin.wallet.bo.base.IPaginableBO;
import com.cdkj.coin.wallet.domain.Collection;
import com.cdkj.coin.wallet.enums.EOriginialCoin;
import com.cdkj.coin.wallet.ethereum.EthAddress;
import com.cdkj.coin.wallet.wanchain.WanAddress;

public interface ICollectionBO extends IPaginableBO<Collection> {

    public String saveCollection(EOriginialCoin coin, String from, String to,
            BigDecimal value, String txHash, String refNo);

    public List<Collection> queryCollectionList(Collection condition);

    public Collection getCollection(String code);

    public Collection getCollectionByTxHash(String txHash);

    public Collection getCollectionByRefNo(String refNo);

    public void doETHCollection(EthAddress ethAddress, String chargeCode);

    public int colectionNoticeETH(Collection data, BigDecimal txfee,
            Date ethDatetime);

    public int colectionNoticeBTC(Collection data, BigDecimal txfee,
            Date btcDatetime);

    public int colectionNoticeSC(Collection data, String fromAddress,
            BigDecimal txfee, Date ethDatetime);

    public void doWanCollection(WanAddress wanAddress, String chargeCode);

    public int colectionNoticeWAN(Collection data, BigDecimal txfee,
            Date wanDatetime);

    // 归集地址使用次数及归集总额查询
    public EthAddress getAddressUseInfo(String toAddress, String currency);

    public BigDecimal getTotalCollect(String currency);

}
