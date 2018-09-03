package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.EthXAddress;

public interface IEthXAddressDAO extends IBaseDAO<EthXAddress> {

    String NAMESPACE = IEthXAddressDAO.class.getName().concat(".");

    public EthXAddress selectSecret(EthXAddress condition);

}
