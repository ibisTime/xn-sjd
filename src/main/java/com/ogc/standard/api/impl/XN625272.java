package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAcceptOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN625272Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 买家取消订单
 * @author: lei 
 * @since: 2018年9月10日 下午8:07:09 
 * @history:
 */
public class XN625272 extends AProcessor {

    private IAcceptOrderAO coinAcceptOrderAO = SpringContextHolder
        .getBean(IAcceptOrderAO.class);

    private XN625272Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        coinAcceptOrderAO.cancelBuyOrder(req.getCode(), req.getUserId(),
            "买家主动取消订单");
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN625272Req.class);
        ObjValidater.validateReq(req);

    }

}
