package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.EthMAddress;

public interface IEthMAddressDAO extends IBaseDAO<EthMAddress> {

    String NAMESPACE = IEthMAddressDAO.class.getName().concat(".");

    public EthMAddress selectSecret(EthMAddress condition);

    public int updateStatus(EthMAddress data);

    public int updateAbandon(EthMAddress data);

}
