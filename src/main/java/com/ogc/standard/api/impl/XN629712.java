package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICartAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629712Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 根据店铺删购物车
 * @author: silver 
 * @since: Nov 15, 2018 7:04:13 PM 
 * @history:
 */
public class XN629712 extends AProcessor {

    private ICartAO cartAO = SpringContextHolder.getBean(ICartAO.class);

    private XN629712Req req;

    @Override
    public Object doBusiness() throws BizException {
        cartAO.dropByShop(req.getShopCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629712Req.class);
        ObjValidater.validateReq(req);
    }

}
