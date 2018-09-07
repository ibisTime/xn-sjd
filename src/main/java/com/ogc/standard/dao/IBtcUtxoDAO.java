package com.ogc.standard.dao;

import java.math.BigDecimal;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.BtcUtxo;

public interface IBtcUtxoDAO extends IBaseDAO<BtcUtxo> {
    String NAMESPACE = IBtcUtxoDAO.class.getName().concat(".");

    public int updateStatus(BtcUtxo data);

    public BigDecimal selectTotalUTXOCount(BtcUtxo data);

    public int updateBroadcast(BtcUtxo data);
}
