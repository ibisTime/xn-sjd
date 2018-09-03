package com.ogc.standard.ao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ogc.standard.ao.ICurrencyRateAO;
import com.ogc.standard.bo.ICurrencyRateBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.CurrencyRate;
import com.ogc.standard.enums.ECurrency;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tianlei on 2017/十一月/13.
 */
@Service
public class CurrencyRateAOImpl implements ICurrencyRateAO {

    private static final Logger logger = LoggerFactory
        .getLogger(CurrencyRateAOImpl.class);

    @Autowired
    ICurrencyRateBO currencyRateBO;

    @Override
    public CurrencyRate currencyRateByCurrency(String currency) {

        return this.currencyRateBO.currencyRateByCurrency(currency);

    }

    // 获取价格计算的汇率
    @Override
    public List<CurrencyRate> allCurrencyRate() {

        List<CurrencyRate> list = new ArrayList<>();

        CurrencyRate uskCurrencyRate = this
            .currencyRateByCurrency(ECurrency.USD.getCode());
        list.add(uskCurrencyRate);

        CurrencyRate udkCurrencyRate = this
            .currencyRateByCurrency(ECurrency.HKD.getCode());
        list.add(udkCurrencyRate);

        return list;
    }

    @Override
    public Paginable<CurrencyRate> queryPage(int start, int limit,
            CurrencyRate condition) {

        return this.currencyRateBO.getPaginable(start, limit, condition);

    }

    public void obtainCurrencyRate() {

        // 真实环境

        String requestStr = "http://web.juhe.cn:8080/finance/exchange/rmbquot?key=04448ab31ce7199b12b7ce8dfe9a5dd3";

        // 测试
        // String requestStr =
        // "http://web.juhe.cn:8080/finance/exchange/rmbquot?key=49dee19f1425e5b9dbeba986f7c17f39";
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder().get().url(requestStr).build();
        Call call = okHttpClient.newCall(request);
        try {

            Response response = call.execute();
            String jsonStr = response.body().string();
            Map map = (Map) JSON.parseObject(jsonStr, HashMap.class);

            if (!map.get("resultcode").equals("200")) {
                return;
            }

            List resultList = (List) map.get("result");
            Map dataMap = (Map) resultList.get(0);

            // {
            // "bankConversionPri":"773.3300",
            // "date":"2017-11-13",
            // "fBuyPri":"771.7200",
            // "fSellPri":"777.1400",
            // "mBuyPri":"747.6800",
            // "mSellPri":"777.1400",
            // "name":"欧元",
            // "time":"14:45:05"
            // }
            BigDecimal usdDecimal = this
                .calculateRate((Map) dataMap.get("data1"));
            BigDecimal hkdDecimal = this
                .calculateRate((Map) dataMap.get("data3"));
            if (usdDecimal == null || hkdDecimal == null) {
                return;
            }

            // 改为刷新数据库
            // 跟新数据库
            CurrencyRate USDCurrencyRate = new CurrencyRate();
            USDCurrencyRate.setOrigin("juhe");
            USDCurrencyRate.setCurrency(ECurrency.USD.getCode());
            USDCurrencyRate.setReferCurrency(ECurrency.CNY.getCode());
            USDCurrencyRate.setRate(usdDecimal);
            USDCurrencyRate.setUpdateDatetime(new Date());
            this.currencyRateBO.insert(USDCurrencyRate);

            CurrencyRate HKDCurrencyRate = new CurrencyRate();
            HKDCurrencyRate.setOrigin("juhe");
            HKDCurrencyRate.setCurrency(ECurrency.HKD.getCode());
            HKDCurrencyRate.setReferCurrency(ECurrency.CNY.getCode());
            HKDCurrencyRate.setRate(hkdDecimal);
            HKDCurrencyRate.setUpdateDatetime(new Date());
            this.currencyRateBO.insert(HKDCurrencyRate);
            //

        } catch (Exception e) {
            logger.error("汇率数据拉取异常，原因：" + e.getMessage());
        }

    }

    private BigDecimal calculateRate(Map rateMap) {

        // 100美元兑 100 人民币
        String rate100Str = (String) rateMap.get("bankConversionPri");
        if (StringUtils.isBlank(rate100Str)) {
            return null;
        }
        return new BigDecimal(rate100Str).divide(new BigDecimal("100"));

    }
}
