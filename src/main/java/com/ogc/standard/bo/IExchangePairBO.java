package com.ogc.standard.bo;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.ExchangePair;

public interface IExchangePairBO extends IPaginableBO<ExchangePair> {

    public Paginable<ExchangePair> queryExchangePairShowPage(int start,
            int pageSize, ExchangePair condition);

}
