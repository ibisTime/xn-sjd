package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ogc.standard.ao.IHuobiproAO;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.domain.ExchangePair;
import com.ogc.standard.domain.ExchangePairHuobiPro;
import com.ogc.standard.domain.MarketHuobiproLog;
import com.ogc.standard.domain.TradingViewKLineModel;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;
import com.ogc.standard.huobipro.HBAccount;
import com.ogc.standard.huobipro.HBBalance;
import com.ogc.standard.huobipro.HBSymbol;
import com.ogc.standard.huobipro.HBUrlInfo;
import com.ogc.standard.market.MarketDepth;
import com.ogc.standard.market.MarketDepthItem;
import com.ogc.standard.util.HMACSHA256;
import com.ogc.standard.util.HttpUtils;
import com.ogc.standard.util.URLEncodeUtil;

/** 
 * 
 * @author: lei 
 * @since: 2018年8月25日 下午11:30:04 
 * @history:
 */
@Service
public class HuobiproAOImpl implements IHuobiproAO {

    private static final String API_URL = "https://api.huobipro.com";

    @Override
    public List<HBSymbol> getSymbols() {

        List<HBSymbol> symbols = new ArrayList<>();

        String requestStr = API_URL + "/v1/common/symbols";

        String jsonStr = HttpUtils.doAccessHTTPGetJson(requestStr);

        JSONObject jsonObject = JSONObject.parseObject(jsonStr);

        JSONArray symbolArray = JSONArray
            .parseArray(jsonObject.getString("data"));

        for (int i = 0; i < symbolArray.size(); i++) {
            JSONObject symbolJson = JSONObject
                .parseObject(symbolArray.getString(i));
            HBSymbol symbol = new HBSymbol();
            symbol.setBaseCurrency(symbolJson.getString("base-currency"));
            symbol.setQuoteCurrency(symbolJson.getString("quote-currency"));
            symbol.setPricePrecision(symbolJson.getString("price-precision"));
            symbol.setAmountPrecision(symbolJson.getString("amount-precision"));
            symbol.setSymbolPartition(symbolJson.getString("symbol-partition"));
            symbols.add(symbol);
        }

        return symbols;
    }

    @Override
    public List<ExchangePairHuobiPro> getExchangPair() {

        List<ExchangePairHuobiPro> pairs = new ArrayList<>();

        String requestStr = "https://api.huobi.pro/market/tickers";

        String jsonStr = HttpUtils.doAccessHTTPGetJson(requestStr);

        JSONObject jsonObject = JSONObject.parseObject(jsonStr);

        JSONArray pairArray = JSONArray
            .parseArray(jsonObject.getString("data"));

        for (int i = 0; i < pairArray.size(); i++) {
            JSONObject symbolJson = JSONObject
                .parseObject(pairArray.getString(i));
            ExchangePairHuobiPro pair = new ExchangePairHuobiPro();
            pair.setOpen(symbolJson.getBigDecimal("open"));
            pair.setClose(symbolJson.getBigDecimal("close"));
            pair.setLow(symbolJson.getBigDecimal("low"));
            pair.setHigh(symbolJson.getBigDecimal("high"));
            pair.setAmount(symbolJson.getBigDecimal("amount"));

            pair.setCount(symbolJson.getBigDecimal("count"));
            pair.setVol(symbolJson.getBigDecimal("vol"));
            pair.setSymbol(symbolJson.getString("symbol"));

            pairs.add(pair);
        }

        return pairs;
    }

    @Override
    public MarketHuobiproLog getTicker(ExchangePair exchangePair) {

        MarketHuobiproLog result = null;

        String requestStr = API_URL + "/market/detail/merged?symbol="
                + exchangePair.getSymbol() + exchangePair.getToSymbol();

        String jsonStr = HttpUtils.doAccessHTTPGetJson(requestStr);

        JSONObject resObject = JSONObject.parseObject(jsonStr);

        if ("ok".equals(resObject.getString("status"))) {

            result = new MarketHuobiproLog();

            JSONObject tickObject = JSONObject
                .parseObject(resObject.getString("tick"));

            result.setSymbol(exchangePair.getSymbol());
            result.setToSymbol(exchangePair.getToSymbol());
            result.setSymbolPair(
                exchangePair.getSymbol() + "/" + exchangePair.getToSymbol());
            result.setkId(tickObject.getString("id"));
            result.setAmount(tickObject.getBigDecimal("amount"));
            result.setCount(tickObject.getBigDecimal("count"));
            result.setOpen(tickObject.getBigDecimal("open"));
            result.setClose(tickObject.getBigDecimal("close"));
            result.setLow(tickObject.getBigDecimal("low"));
            result.setHigh(tickObject.getBigDecimal("high"));
            result.setVol(tickObject.getBigDecimal("vol"));

            result.setBidPrice(tickObject.getJSONArray("bid").getBigDecimal(0));
            result
                .setBidAmount(tickObject.getJSONArray("bid").getBigDecimal(1));
            result.setAskPrice(tickObject.getJSONArray("ask").getBigDecimal(0));
            result
                .setAskAmount(tickObject.getJSONArray("ask").getBigDecimal(1));

            result.setTs(resObject.getString("ts"));
            result.setCreateDatetime(new Date());
        }

        return result;
    }

    @Override
    public MarketDepth getMarketDepth(String symbol, String type) {

        MarketDepth result = null;

        try {
            String requestStr = API_URL + "/market/depth?symbol=" + symbol
                    + "&type=" + type;

            String jsonStr = HttpUtils.doAccessHTTPGetJson(requestStr);

            JSONObject resObject = JSONObject.parseObject(jsonStr);

            if ("ok".equals(resObject.getString("status"))) {

                result = new MarketDepth();

                JSONArray bids = resObject.getJSONObject("tick")
                    .getJSONArray("bids");
                JSONArray asks = resObject.getJSONObject("tick")
                    .getJSONArray("asks");

                List<MarketDepthItem> bidsList = new ArrayList<>();
                List<MarketDepthItem> asksList = new ArrayList<>();

                for (int i = 0; i < 5; i++) {

                    MarketDepthItem bid = new MarketDepthItem();
                    bid.setPrice(bids.getJSONArray(i).getBigDecimal(0));
                    bid.setAmount(bids.getJSONArray(i).getBigDecimal(1));
                    bidsList.add(bid);

                    MarketDepthItem ask = new MarketDepthItem();
                    ask.setPrice(asks.getJSONArray(i).getBigDecimal(0));
                    ask.setAmount(asks.getJSONArray(i).getBigDecimal(1));
                    asksList.add(ask);

                }

                result.setBids(bidsList);
                result.setAsks(asksList);

            }
            // else {
            // throw new BizException(EBizErrorCode.DEFAULT.getCode(),
            // "数据请求发生错误" + resObject.getString("err-msg"));
            // }
        } catch (Exception e) {

            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "获取火币pro交易深度失败，" + e.getMessage());
        }

        return result;
    }

    @Override
    public TradingViewKLineModel getTradingViewKLineModels(String symbolPair,
            String period, Integer size) {

        TradingViewKLineModel tradingViewKLineModel = null;

        try {
            String requestStr = "https://api.huobipro.com/market/history/kline?period="
                    + period + "&size=" + size + "&symbol=" + symbolPair;

            String jsonStr = HttpUtils.doAccessHTTPGetJson(requestStr);

            JSONObject resObject = JSONObject.parseObject(jsonStr);

            if ("ok".equals(resObject.getString("status"))) {

                tradingViewKLineModel = new TradingViewKLineModel();

                JSONArray kLineArray = JSONArray
                    .parseArray(resObject.getString("data"));

                // 状态码 ok error no_data
                String s = resObject.getString("status");

                List<BigDecimal> c = new ArrayList<BigDecimal>();

                // 最高价
                List<BigDecimal> h = new ArrayList<BigDecimal>();

                // 最低价
                List<BigDecimal> l = new ArrayList<BigDecimal>();

                // 开盘价
                List<BigDecimal> o = new ArrayList<BigDecimal>();

                // K线时间，unix时间戳
                List<BigInteger> t = new ArrayList<BigInteger>();

                // 成交量
                List<BigDecimal> v = new ArrayList<BigDecimal>();

                for (int i = 0; i < kLineArray.size(); i++) {
                    JSONObject kLine = JSONObject
                        .parseObject(kLineArray.getString(i));
                    c.add(kLine.getBigDecimal("close"));
                    h.add(kLine.getBigDecimal("high"));
                    l.add(kLine.getBigDecimal("low"));
                    o.add(kLine.getBigDecimal("open"));
                    t.add(kLine.getBigInteger("id"));
                    v.add(kLine.getBigDecimal("amount"));
                }

                tradingViewKLineModel.setS(s);
                tradingViewKLineModel.setC(c);
                tradingViewKLineModel.setH(h);
                tradingViewKLineModel.setL(l);
                tradingViewKLineModel.setO(o);
                tradingViewKLineModel.setT(t);
                tradingViewKLineModel.setV(v);

            }
            // else {
            // throw new BizException(EBizErrorCode.DEFAULT.getCode(),
            // "数据请求发生错误" + resObject.getString("err-msg"));
            // }
        } catch (Exception e) {
            // throw new BizException(EBizErrorCode.DEFAULT.getCode(),
            // "获取火币proK线数据失败，" + e.getMessage());
        }

        return tradingViewKLineModel;
    }

    @Override
    public HBAccount getAccount(String accessKey, String secretKey,
            String type) {

        HBAccount hbpAccount = null;

        try {

            Map<String, String> params = new HashMap<String, String>();

            HBUrlInfo urlInfo = getUrlInfo("GET", "api.huobipro.com",
                "/v1/account/accounts", params, accessKey, secretKey);

            String requestStr = API_URL + "/v1/account/accounts?"
                    + urlInfo.getUrl();

            String jsonStr = HttpUtils.doAccessHTTPGetJson(requestStr);

            JSONObject resObject = JSONObject.parseObject(jsonStr);

            if ("ok".equals(resObject.getString("status"))) {

                JSONArray accountArray = resObject.getJSONArray("data");

                for (int i = 0; i < accountArray.size(); i++) {
                    JSONObject accountJson = accountArray.getJSONObject(i);
                    if (accountJson.getString("type").equals(type)) {
                        hbpAccount = JsonUtil.json2Bean(accountJson.toString(),
                            HBAccount.class);
                    }
                }

            } else {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "数据请求发生错误" + resObject.getString("err-msg"));
            }

        } catch (Exception e) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "获取账户信息失败，" + e.getMessage());
        }

        return hbpAccount;
    }

    @Override
    public List<HBBalance> getBalance(String accessKey, String secretKey) {

        List<HBBalance> balances = new ArrayList<HBBalance>();

        HBAccount account = getAccount(accessKey, secretKey, "spot");

        if (account == null) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "未找到现货账户");
        }
        if ("lock".equals(account.getState())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "现货账户被锁定");
        }

        String reqPath = "/v1/account/accounts/" + account.getId() + "/balance";

        try {

            Map<String, String> params = new HashMap<String, String>();

            HBUrlInfo urlInfo = getUrlInfo("GET", "api.huobipro.com", reqPath,
                params, accessKey, secretKey);

            String requestStr = API_URL + reqPath + "?" + urlInfo.getUrl();

            String jsonStr = HttpUtils.doAccessHTTPGetJson(requestStr);

            JSONObject resObject = JSONObject.parseObject(jsonStr);

            if ("ok".equals(resObject.getString("status"))) {

                JSONArray balanceArray = resObject.getJSONObject("data")
                    .getJSONArray("list");

                for (int i = 0; i < balanceArray.size(); i++) {

                    HBBalance balance = JsonUtil
                        .json2Bean(balanceArray.getString(i), HBBalance.class);
                    // // 只获取余额大于0的账户
                    // if (new BigDecimal(balance.getBalance())
                    // .compareTo(BigDecimal.ZERO) > 0) {
                    balances.add(balance);
                    // }

                }

            } else {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "数据请求发生错误" + resObject.getString("err-msg"));
            }

        } catch (Exception e) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "获取账户余额失败，" + e.getMessage());
        }

        return balances;

    }

    private HBUrlInfo getUrlInfo(String requestMethod, String host, String path,
            Map<String, String> params, String accessKey, String secretKey) {

        // 需要签名(需要拼接在url上的参数)
        Map<String, String> signatureParams = getRequiredParams(accessKey);

        // 不需要签名(在body上的参数)
        Map<String, String> noSignatureParams = new HashMap<String, String>();

        if ("GET".equals(requestMethod)) {
            signatureParams.putAll(params);
        } else if ("POST".equals(requestMethod)) {
            noSignatureParams.putAll(params);
        }

        // 加密前先将值进行url转码
        signatureParams = getEncodedparam(signatureParams);
        // 需要加密的参数名称（ascii排过序的）
        String orderedParam = createLinkString(signatureParams);

        StringBuffer signatureOriginalStr = new StringBuffer("");
        signatureOriginalStr.append(requestMethod).append("\n");
        signatureOriginalStr.append(host).append("\n");
        signatureOriginalStr.append(path).append("\n");

        signatureOriginalStr.append(orderedParam);

        // HMACSHA256加密
        String base64Sign = HMACSHA256
            .sha256_HMAC(signatureOriginalStr.toString(), secretKey);

        // URL转码成最终的签名字符串
        String signature = URLEncodeUtil.encode(base64Sign);

        HBUrlInfo result = new HBUrlInfo();
        result.setUrl(orderedParam + "&Signature=" + signature);
        result.setParames(noSignatureParams);

        return result;

    }

    /** 
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    private String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

    private Map<String, String> getEncodedparam(Map<String, String> params) {
        Map<String, String> encodedParams = new HashMap<String, String>();
        Set<String> keys = params.keySet();
        for (String key : keys) {
            encodedParams.put(key, URLEncodeUtil.encode(params.get(key)));
        }
        return encodedParams;
    }

    private Map<String, String> getRequiredParams(String accessKey) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("AccessKeyId", accessKey);
        params.put("SignatureMethod", "HmacSHA256");
        params.put("SignatureVersion", "2");
        params.put("Timestamp", DateUtil.getUTCTimeStr());

        return params;

    }

}
