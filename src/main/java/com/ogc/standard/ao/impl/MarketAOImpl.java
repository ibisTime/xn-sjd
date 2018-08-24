package com.ogc.standard.ao.impl;

import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IMarketAO;
import com.ogc.standard.market.MarketDepth;

/** 
 * 
 * @author: lei 
 * @since: 2018年8月22日 下午8:15:15 
 * @history:
 */
@Service
public class MarketAOImpl implements IMarketAO {

    // @Autowired
    // IHuobiproAO huobiproAO;

    @Override
    public MarketDepth getMarketDepth(String symbolPair, String exchange) {

        MarketDepth marketDepth = null;

        symbolPair = symbolPair.replace("/", "").toLowerCase();
        // marketDepth = huobiproAO.getMarketDepth(symbolPair, "step1");

        return marketDepth;
    }

}
