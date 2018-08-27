package com.ogc.standard.dao;

import com.cdkj.coin.wallet.dao.base.IBaseDAO;
import com.cdkj.coin.wallet.domain.Coin;

public interface ICoinDAO extends IBaseDAO<Coin> {
    String NAMESPACE = ICoinDAO.class.getName().concat(".");

    public int update(Coin data);

    public int updateStatus(Coin data);
}
