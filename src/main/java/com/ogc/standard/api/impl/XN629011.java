package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IProductAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629011Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 修改产品
 * @author: silver 
 * @since: 2018年9月27日 上午9:50:34 
 * @history:
 */
public class XN629011 extends AProcessor {
    private IProductAO productAO = SpringContextHolder
        .getBean(IProductAO.class);

    private XN629011Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        productAO.editProduct(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629011Req.class);
        ObjValidater.validateReq(req);
    }
}
