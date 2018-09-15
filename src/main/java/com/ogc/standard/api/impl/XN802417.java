package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IDivideDetailAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN802417Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 分红明细详情查询
 * @author: lei 
 * @since: 2018年9月15日 下午5:12:07 
 * @history:
 */
public class XN802417 extends AProcessor {

    private IDivideDetailAO divideDetailAO = SpringContextHolder
        .getBean(IDivideDetailAO.class);

    private XN802417Req req;

    @Override
    public Object doBusiness() throws BizException {

        return divideDetailAO.getDivideDetail(req.getId());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802417Req.class);
        ObjValidater.validateReq(req);
    }

}
