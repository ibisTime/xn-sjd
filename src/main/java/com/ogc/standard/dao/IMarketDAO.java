package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.Market;

import java.math.BigDecimal;

/**
 * Created by tianlei on 2017/十一月/13.
 */
public interface IMarketDAO extends IBaseDAO<Market> {

    String NAMESPACE = IMarketDAO.class.getName().concat(".");

    int update(Market market);

    BigDecimal selectMarketAvg(Market condition);

}
