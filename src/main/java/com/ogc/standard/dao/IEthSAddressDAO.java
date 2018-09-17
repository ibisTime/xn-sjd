package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.EthSAddress;

public interface IEthSAddressDAO extends IBaseDAO<EthSAddress> {

    String NAMESPACE = IEthSAddressDAO.class.getName().concat(".");

    public EthSAddress selectSecret(EthSAddress condition);

    public int updateStatus(EthSAddress data);

    public int updateAbandon(EthSAddress data);

}
