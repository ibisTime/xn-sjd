package com.ogc.standard.ao;

import java.util.List;

import com.ogc.standard.domain.ExchangePair;
import com.ogc.standard.domain.ExchangePairHuobiPro;
import com.ogc.standard.domain.MarketHuobiproLog;
import com.ogc.standard.domain.TradingViewKLineModel;
import com.ogc.standard.huobipro.HBAccount;
import com.ogc.standard.huobipro.HBBalance;
import com.ogc.standard.huobipro.HBSymbol;
import com.ogc.standard.market.MarketDepth;

/** 
 * 
 * @author: lei 
 * @since: 2018年8月25日 下午11:19:25 
 * @history:
 */
public interface IHuobiproAO {

    public List<HBSymbol> getSymbols();

    /**
     * 获取火币Pro支持的交易对价格
     * @return 
     * @create: 2018年8月26日 下午5:34:41 lei
     * @history:
     */
    public List<ExchangePairHuobiPro> getExchangPair();

    /**
     * 获取pro站支持的交易对信息
     * @param exchangePair 交易对
     * @return 
     * @create: 2018年4月12日 下午4:22:49 haiqingzheng
     * @history:
     */
    public MarketHuobiproLog getTicker(ExchangePair exchangePair);

    /**
     * 获取交易深度信息
     * @param symbol 交易对 btcusdt
     * @param type step0, step1, step2, step3, step4, step5（合并深度0-5）；step0时，不合并深度
     * @return 
     * @create: 2018年4月12日 下午4:21:28 haiqingzheng
     * @history:
     */
    public MarketDepth getMarketDepth(String symbol, String type);

    /**
     * 获取K线数据
     * @param symbolPair
     * @param period
     * @param size
     * @return 
     * @create: 2018年4月13日 下午1:43:09 haiqingzheng
     * @history:
     */
    public TradingViewKLineModel getTradingViewKLineModels(String symbolPair,
            String period, Integer size);

    /**
     * 获取用户账户
     * @param accessKey
     * @param secretKey 
     * @create: 2018年4月14日 上午11:14:16 haiqingzheng
     * @history:
     */
    public HBAccount getAccount(String accessKey, String secretKey,
            String type);

    /**
     * 获取用户资产
     * @param accessKey 访问秘钥
     * @param secretKey 私密秘钥
     * @create: 2018年4月13日 下午1:56:19 haiqingzheng
     * @history:
     */
    public List<HBBalance> getBalance(String accessKey, String secretKey);

}
