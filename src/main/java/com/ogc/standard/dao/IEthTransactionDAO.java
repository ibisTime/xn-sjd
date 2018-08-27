package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.EthTransaction;

public interface IEthTransactionDAO extends IBaseDAO<EthTransaction> {
    String NAMESPACE = IEthTransactionDAO.class.getName().concat(".");
}
