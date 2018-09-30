package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IGiftOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629321Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 删除礼物订单
 * @author: jiafr 
 * @since: 2018年9月30日 上午11:16:26 
 * @history:
 */
public class XN629321 extends AProcessor {
    private IGiftOrderAO giftOrderAO = SpringContextHolder
        .getBean(IGiftOrderAO.class);

    private XN629321Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        giftOrderAO.dropGiftOrder(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629321Req.class);
        ObjValidater.validateReq(req);
    }
}
