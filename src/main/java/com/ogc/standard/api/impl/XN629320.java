package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IGiftOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629320Req;
import com.ogc.standard.dto.res.PKCodeRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 新增礼物订单
 * @author: jiafr 
 * @since: 2018年9月30日 上午10:57:09 
 * @history:
 */
public class XN629320 extends AProcessor {
    private IGiftOrderAO giftOrderAO = SpringContextHolder
        .getBean(IGiftOrderAO.class);

    private XN629320Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(giftOrderAO.addGiftOrder(req.getAdoptTreeCode(),
            req.getName(), req.getPrice(), req.getDescription(),
            req.getInvalidDatetime()));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629320Req.class);
        ObjValidater.validateReq(req);
    }
}
