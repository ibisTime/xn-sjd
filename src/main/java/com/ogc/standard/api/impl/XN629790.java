package com.ogc.standard.api.impl;

import java.math.BigDecimal;

import com.ogc.standard.ao.IDefaultPostageAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629790Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 修改默认邮费
 * @author: silver 
 * @since: Dec 5, 2018 2:47:10 PM 
 * @history:
 */
public class XN629790 extends AProcessor {

    private IDefaultPostageAO defaultPostageAO = SpringContextHolder
        .getBean(IDefaultPostageAO.class);

    private XN629790Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        BigDecimal price = new BigDecimal(req.getPrice());

        defaultPostageAO.editDefaultPostage(req.getCode(), price,
            req.getUpdater());

        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629790Req.class);
        ObjValidater.validateReq(req);
    }
}
