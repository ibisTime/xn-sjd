package com.ogc.standard.bo;

import java.math.BigDecimal;
import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.BtcUtxo;
import com.ogc.standard.domain.CtqBtcUtxo;
import com.ogc.standard.enums.EAddressType;
import com.ogc.standard.enums.EBtcUtxoRefType;
import com.ogc.standard.enums.EBtcUtxoStatus;

public interface IBtcUtxoBO extends IPaginableBO<BtcUtxo> {

    public boolean isBtcUtxoExist(String txid, Integer vout);

    public int saveBtcUtxo(CtqBtcUtxo ctqBtcUtxo, EAddressType addressType);

    public int refreshBroadcast(BtcUtxo data, EBtcUtxoStatus status,
            EBtcUtxoRefType refType, String refNo);

    public int refreshStatus(BtcUtxo data, EBtcUtxoStatus status);

    public List<BtcUtxo> queryBtcUtxoList(BtcUtxo condition);

    public List<BtcUtxo> queryBtcUtxoList(String refNo);

    public BtcUtxo getBtcUtxo(String txid, Integer vout);

    public List<BtcUtxo> selectUnPush();

    // 可花费的UTXO总和
    public BigDecimal getTotalEnableUTXOCount(EAddressType addressType);

    // 某地址可花费的UTXO总和
    public BigDecimal getTotalEnableUTXOCount(String address);

    List<BtcUtxo> queryEnableUtxoList(int start, int limit,
            EAddressType addressType);
}
