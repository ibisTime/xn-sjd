package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICurrencyRateAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 列表查所有
 * @author: dl 
 * @since: 2018年8月27日 下午10:33:55 
 * @history:
 */
public class XN802036 extends AProcessor {

    private ICurrencyRateAO currencyRateAO = SpringContextHolder
        .getBean(ICurrencyRateAO.class);

    @Override
    public Object doBusiness() throws BizException {
        return this.currencyRateAO.allCurrencyRate();
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {

    }
}
