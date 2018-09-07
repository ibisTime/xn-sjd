package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bitcoin.original.BTCOriginalTx;
import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.BtcTransaction;

public interface IBtcTransactionBO extends IPaginableBO<BtcTransaction> {

    public boolean isBtcTransactionExist(String txid);

    public int saveBtcTransaction(BTCOriginalTx btcOriginalTx, String refNo);

    public List<BtcTransaction> queryBtcTransactionList(
            BtcTransaction condition);

    public BtcTransaction getBtcTransaction(String txid);

}
