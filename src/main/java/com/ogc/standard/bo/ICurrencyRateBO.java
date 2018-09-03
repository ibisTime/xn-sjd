package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.CurrencyRate;

/**
 * Created by tianlei on 2017/十一月/13.
 */
public interface ICurrencyRateBO extends IPaginableBO<CurrencyRate> {

    CurrencyRate currencyRateByCurrency(String currency);

    int insert(CurrencyRate currencyRate);

    List<CurrencyRate> allCurrencyRate();

    // BigDecimal getAVGRate(ECurrency currency);

}
