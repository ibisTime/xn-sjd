package com.ogc.standard.dao;

import java.math.BigDecimal;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.EthXAddress;

public interface IEthXAddressDAO extends IBaseDAO<EthXAddress> {
    String NAMESPACE = IEthXAddressDAO.class.getName().concat(".");

    public int updateAbandon(EthXAddress data);

    public int updateBalance(EthXAddress data);

    public EthXAddress selectSecret(EthXAddress condition);

    public BigDecimal selectTotalBalance(EthXAddress condition);

    public int updateStatus(EthXAddress data);
}
