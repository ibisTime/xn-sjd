package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAdoptOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629042Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 支付认养订单
 * @author: jiafr 
 * @since: 2018年9月27日 上午11:40:14 
 * @history:
 */
public class XN629042 extends AProcessor {
    private IAdoptOrderAO adoptOrderAO = SpringContextHolder
        .getBean(IAdoptOrderAO.class);

    private XN629042Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return adoptOrderAO.toPayAdoptOrder(req.getCode(), req.getPayType(),
            req.getIsJfDeduct(), req.getTradePwd());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629042Req.class);
        ObjValidater.validateReq(req);
    }

}
