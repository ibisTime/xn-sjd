package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.AcceptOrder;

public interface IAcceptOrderDAO extends IBaseDAO<AcceptOrder> {
    String NAMESPACE = IAcceptOrderDAO.class.getName().concat(".");

    public int updateCancel(AcceptOrder tradeOrder);

    public int updateMarkPay(AcceptOrder tradeOrder);
}
