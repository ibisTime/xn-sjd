package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.EthWAddress;

public interface IEthWAddressDAO extends IBaseDAO<EthWAddress> {

    String NAMESPACE = IEthWAddressDAO.class.getName().concat(".");

    public int updateAbandon(EthWAddress data);

}
