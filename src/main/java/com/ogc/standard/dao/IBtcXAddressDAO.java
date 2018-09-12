package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.BtcXAddress;

public interface IBtcXAddressDAO extends IBaseDAO<BtcXAddress> {
    String NAMESPACE = IBtcXAddressDAO.class.getName().concat(".");

    public int updateAbandon(BtcXAddress data);

    public int updateStatus(BtcXAddress data);
}
