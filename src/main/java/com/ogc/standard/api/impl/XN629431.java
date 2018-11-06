package com.ogc.standard.api.impl;

import java.math.BigDecimal;

import com.ogc.standard.ao.IOriginalGroupAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN629431Req;
import com.ogc.standard.dto.res.PKCodeRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 二维码转让
 * @author: silver 
 * @since: Nov 4, 2018 2:57:06 PM 
 * @history:
 */
public class XN629431 extends AProcessor {
    private IOriginalGroupAO originalGroupAO = SpringContextHolder
        .getBean(IOriginalGroupAO.class);

    private XN629431Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Integer quantity = StringValidater.toInteger(req.getQuantity());
        BigDecimal price = new BigDecimal(req.getPrice());
        return new PKCodeRes(
            originalGroupAO.qrSales(req.getCode(), price, quantity));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629431Req.class);
        ObjValidater.validateReq(req);
    }

}
