package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Charge;
import com.ogc.standard.domain.EthWAddress;

public interface IChargeDAO extends IBaseDAO<Charge> {
    String NAMESPACE = IChargeDAO.class.getName().concat(".");

    void payOrder(Charge data);

    public EthWAddress selectAddressUseInfo(Charge data);
}
