package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.ExchangePair;

public interface IExchangePairBO extends IPaginableBO<ExchangePair> {

    public List<ExchangePair> queryExchangePairList(ExchangePair condition);

    public Paginable<ExchangePair> queryExchangePairShowPage(int start,
            int pageSize, ExchangePair condition);

    public void updatePrice(ExchangePair data);

}
