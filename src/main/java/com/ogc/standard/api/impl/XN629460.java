package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IPresellLogisticsAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629460Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 发货
 * @author: silver 
 * @since: Nov 5, 2018 8:10:43 PM 
 * @history:
 */
public class XN629460 extends AProcessor {
    private IPresellLogisticsAO presellLogisticsAO = SpringContextHolder
        .getBean(IPresellLogisticsAO.class);

    private XN629460Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        presellLogisticsAO.send(req.getCode(), req.getDeliver(),
            req.getLogisticsCompany(), req.getLogisticsNumber());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629460Req.class);
        ObjValidater.validateReq(req);
    }

}
