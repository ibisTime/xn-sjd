package com.ogc.standard.dao;

import com.cdkj.coin.wallet.dao.base.IBaseDAO;
import com.cdkj.coin.wallet.domain.Charge;
import com.cdkj.coin.wallet.ethereum.EthAddress;

public interface IChargeDAO extends IBaseDAO<Charge> {
    String NAMESPACE = IChargeDAO.class.getName().concat(".");

    void payOrder(Charge data);

    public EthAddress selectAddressUseInfo(Charge data);
}
