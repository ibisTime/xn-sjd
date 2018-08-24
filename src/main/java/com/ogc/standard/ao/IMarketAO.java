package com.ogc.standard.ao;

import com.ogc.standard.market.MarketDepth;

/** 
 * 
 * @author: lei 
 * @since: 2018年8月22日 下午8:10:51 
 * @history:
 */
public interface IMarketAO {

    public MarketDepth getMarketDepth(String symbolPair, String exchange);

}
