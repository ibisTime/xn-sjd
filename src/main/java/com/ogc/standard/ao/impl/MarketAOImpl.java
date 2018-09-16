package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IHuobiproAO;
import com.ogc.standard.ao.IMarketAO;
import com.ogc.standard.bo.IMarketBO;
import com.ogc.standard.bo.ISYSConfigBO;
import com.ogc.standard.common.SysConstants;
import com.ogc.standard.domain.Market;
import com.ogc.standard.enums.ECoin;
import com.ogc.standard.enums.EExchange;
import com.ogc.standard.exception.BizException;
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
    private IHuobiproAO huobiproAO;

    @Autowired
    private IMarketBO marketBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

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

    @Override
    public Market coinPriceByPlatform(String coin, String refCurrency) {

        // 获取平台调控值
        BigDecimal x = BigDecimal.ZERO;

        ECoin eCoin = null;
        if (coin.equals(ECoin.ETH.getCode())) {
            eCoin = ECoin.ETH;
            x = this.sysConfigBO
                .getBigDecimalValue(SysConstants.ETH_COIN_PRICE_X);
        } else if (coin.equals(ECoin.X.getCode())) {
            eCoin = ECoin.X;
            x = this.sysConfigBO
                .getBigDecimalValue(SysConstants.X_COIN_PRICE_X);
        } else if (coin.equals(ECoin.BTC.getCode())) {
            eCoin = ECoin.BTC;
            x = this.sysConfigBO
                .getBigDecimalValue(SysConstants.BTC_COIN_PRICE_X);
        }

        if (eCoin == null) {
            throw new BizException("xn000", coin + "不支持的货币类型");
        }

        Market market = this.marketBO.standardMarket(eCoin, refCurrency);

        // 计算平台调控过的值
        market.setMid(market.getMid().add(x));
        return market;

    }

}
