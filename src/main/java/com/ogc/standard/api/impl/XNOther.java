package com.ogc.standard.api.impl;

import com.ogc.standard.api.AProcessor;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;

public class XNOther extends AProcessor {

    @Override
    public Object doBusiness() throws BizException {
        throw new BizException("702xxx", "无效API功能号");
    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        throw new ParaException("702xxx", "无效API功能号");

    }

}
