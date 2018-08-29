package com.ogc.standard.ao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IExchangePairAO;
import com.ogc.standard.ao.IHuobiproAO;
import com.ogc.standard.bo.IExchangePairBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.ExchangePair;
import com.ogc.standard.domain.ExchangePairHuobiPro;

@Service
public class ExchangePairAOImpl implements IExchangePairAO {

    @Autowired
    private IExchangePairBO exchangePairBO;

    @Autowired
    private IHuobiproAO huobiproAO;

    @Override
    public Paginable<ExchangePair> queryExchangePairPage(int start, int limit,
            ExchangePair condition) {
        return exchangePairBO.queryExchangePairShowPage(start, limit,
            condition);
    }

    public void doRefreshExchangePairPrice() {

        // 获取火币支持的所有交易对信息
        List<ExchangePairHuobiPro> huobiPairs = huobiproAO.getExchangPair();

        // 获取平台内的所有火币交易对
        List<ExchangePair> pairs = exchangePairBO
            .queryExchangePairList(new ExchangePair());

        // 遍历并更新系统内火币交易对的价格
        for (ExchangePair pair : pairs) {

            for (ExchangePairHuobiPro huobiPair : huobiPairs) {

                if (pair.getPair().equals(huobiPair.getSymbol())) {

                    // 更新价格
                    pair.setPrice(huobiPair.getClose());
                    pair.setUpdater("system");
                    pair.setUpdateDatetime(new Date());
                    exchangePairBO.updatePrice(pair);

                }

            }

        }

    }
}
