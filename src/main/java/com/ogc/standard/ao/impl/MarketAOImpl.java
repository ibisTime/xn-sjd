package com.ogc.standard.ao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IHuobiproAO;
import com.ogc.standard.ao.IMarketAO;
import com.ogc.standard.enums.EExchange;
import com.ogc.standard.market.MarketDepth;
import com.ogc.standard.market.MarketDepthItem;

/** 
 * 
 * @author: lei 
 * @since: 2018年8月22日 下午8:15:15 
 * @history:
 */
@Service
public class MarketAOImpl implements IMarketAO {

    @Autowired
    IHuobiproAO huobiproAO;

    @Override
    public MarketDepth getMarketDepth(String symbolPair, String exchange) {

        MarketDepth marketDepth = null;

        if (EExchange.HuobiPro.getCode().equals(exchange)) {
            symbolPair = symbolPair.replace("/", "").toLowerCase();
            marketDepth = huobiproAO.getMarketDepth(symbolPair, "step1");
        } else {
            marketDepth = new MarketDepth();
            marketDepth.setAsks(new ArrayList<MarketDepthItem>());
            marketDepth.setBids(new ArrayList<MarketDepthItem>());
        }

        return marketDepth;
    }

}
