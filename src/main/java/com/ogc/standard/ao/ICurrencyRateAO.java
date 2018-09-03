package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.CurrencyRate;

/**
 * Created by tianlei on 2017/十一月/13.
 */
public interface ICurrencyRateAO {

    CurrencyRate currencyRateByCurrency(String currency);

    List<CurrencyRate> allCurrencyRate();

    public Paginable<CurrencyRate> queryPage(int start, int limit,
            CurrencyRate condition);

}
