package com.ogc.standard.dao;

import java.util.List;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.ExchangePair;

//dao层 
public interface IExchangePairDAO extends IBaseDAO<ExchangePair> {
    String NAMESPACE = IExchangePairDAO.class.getName().concat(".");

    public List<ExchangePair> selectListShow(ExchangePair condition, int start,
            int count);

    public int updatePrice(ExchangePair data);
}
