package com.ogc.standard.dao;

import java.util.List;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.EthXAddress;

public interface IEthXAddressDAO extends IBaseDAO<EthXAddress> {

    String NAMESPACE = IEthXAddressDAO.class.getName().concat(".");

    public EthXAddress selectSecret(EthXAddress condition);

    public List<EthXAddress> selectNeedCollectList(EthXAddress condition,
            int start, int limit);
}
