package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.domain.Market;
import com.ogc.standard.dto.req.XN625291Req;
import com.ogc.standard.market.MarketDepth;

/** 
 * 
 * @author: lei 
 * @since: 2018年8月22日 下午8:10:51 
 * @history:
 */
public interface IMarketAO {

    public List<Market> marketListByReq(XN625291Req req);

    public MarketDepth getMarketDepth(String symbolPair, String exchange);

}
