package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ITradeOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 
 * @author: haiqingzheng 
 * @since: 2017年12月15日 下午12:59:19 
 * @history:
 */
public class XN625259 extends AProcessor {

    private ITradeOrderAO tradeOrderAO = SpringContextHolder
        .getBean(ITradeOrderAO.class);

    @Override
    public Object doBusiness() throws BizException {

        tradeOrderAO.createGroupIfNotExist();
        return new BooleanRes(true);

    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {

    }
}
