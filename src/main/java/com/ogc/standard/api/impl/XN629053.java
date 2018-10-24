package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IGroupAdoptOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629053Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 支付集体认养订单
 * @author: jiafr 
 * @since: 2018年9月29日 下午1:49:25 
 * @history:
 */
public class XN629053 extends AProcessor {
    private IGroupAdoptOrderAO groupAdoptOrderAO = SpringContextHolder
        .getBean(IGroupAdoptOrderAO.class);

    private XN629053Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return groupAdoptOrderAO.toPayAdoptOrder(req.getCode(),
            req.getPayType(), req.getIsJfDeduct(), req.getTradePwd());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629053Req.class);
        ObjValidater.validateReq(req);
    }
}
