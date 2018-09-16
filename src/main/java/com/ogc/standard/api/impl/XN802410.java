package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IDivideAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN802410Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 分红
 * @author: lei 
 * @since: 2018年9月15日 下午5:12:07 
 * @history:
 */
public class XN802410 extends AProcessor {

    private IDivideAO divideAO = SpringContextHolder.getBean(IDivideAO.class);

    private XN802410Req req;

    @Override
    public Object doBusiness() throws BizException {

        divideAO.doDivide(req.getId(), req.getDivideProfit(),
            req.getDivideUser(), req.getRemark());

        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802410Req.class);
        ObjValidater.validateReq(req);
    }

}
