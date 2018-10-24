package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAdoptOrderTreeAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 测试接口
 * @author: silver 
 * @since: Oct 10, 2018 4:47:04 PM 
 * @history:
 */
public class XN000000 extends AProcessor {
    private IAdoptOrderTreeAO adoptOrderTreeAO = SpringContextHolder
        .getBean(IAdoptOrderTreeAO.class);

    @Override
    public Object doBusiness() throws BizException {
        adoptOrderTreeAO.doDailyAdoptOrderTree();
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
    }
}