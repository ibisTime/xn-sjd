package com.ogc.standard.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IExchangePairAO;
import com.ogc.standard.bo.IExchangePairBO;
import com.ogc.standard.bo.ISimuKLineBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.ExchangePair;
import com.ogc.standard.match.SimuOrderMatch;

@Service
public class ExchangePairAOImpl implements IExchangePairAO {

    @Autowired
    private IExchangePairBO exchangePairBO;

    @Autowired
    private SimuOrderMatch simuOrderMatch;

    @Autowired
    private ISimuKLineBO simuKLineBO;

    @Override
    public Paginable<ExchangePair> queryExchangePairPage(int start, int limit,
            ExchangePair condition) {
        return exchangePairBO.queryExchangePairShowPage(start, limit,
            condition);
    }

    // 扫描平台支持的交易对
    public void scanPair() {

        // 获取平台内的所有交易对
        // List<ExchangePair> pairs = exchangePairBO
        // .queryExchangePairList(new ExchangePair());
        //
        // for (ExchangePair pair : pairs) {
        //
        // // new Thread() {
        // // public void run() {
        // simuOrderMatch.doMatchTrade(pair.getSymbol().toUpperCase(),
        // pair.getToSymbol().toUpperCase());
        // // }
        // // }.start();
        //
        // }

    }

    // 扫描平台支持的交易对
    public void saveKLineMin1() {

        // 获取平台内的所有交易对
        List<ExchangePair> pairs = exchangePairBO
            .queryExchangePairList(new ExchangePair());

        for (ExchangePair pair : pairs) {

            simuKLineBO.saveKLineByPeriod(pair, -1);

        }

    }

}
