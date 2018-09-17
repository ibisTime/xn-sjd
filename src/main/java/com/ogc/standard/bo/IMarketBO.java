package com.ogc.standard.bo;

import java.util.List;

import com.ogc.standard.domain.Market;
import com.ogc.standard.enums.ECoin;

/**
 * Created by tianlei on 2017/十一月/13.
 */
public interface IMarketBO {

    /*
     * 获取价格计算的标准, 取加权值 + 系统可配参数
     */
    public Market standardMarket(ECoin coin, String refCurrency);

    public Market marketByCoinTypeAndOrigin(String coinType, String origin);

    int updateMarket(String origin, String cointType, Market market);

    List<Market> marketListByCondation(Market condation);

//    BigDecimal getMarketAVG(ECoin coin);

}
