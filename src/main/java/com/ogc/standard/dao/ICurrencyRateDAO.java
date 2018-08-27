package com.ogc.standard.dao;

import com.ogc.standard.dao.base.IBaseDAO;
import com.ogc.standard.domain.CurrencyRate;

/**
 * Created by tianlei on 2017/十一月/13.
 */
public interface ICurrencyRateDAO extends IBaseDAO<CurrencyRate> {

    String NAMESPACE = ICurrencyRateDAO.class.getName().concat(".");

    int update(CurrencyRate currencyRate);

    // BigDecimal selectRateAVG(CurrencyRate condition);

}
