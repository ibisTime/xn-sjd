package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.BtcTransaction;

public interface IBtcTransactionDAO extends IBaseDAO<BtcTransaction> {
    String NAMESPACE = IBtcTransactionDAO.class.getName().concat(".");
}
