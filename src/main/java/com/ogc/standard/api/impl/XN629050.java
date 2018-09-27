package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IGroupAdoptOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629050Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 第一人下单集体认养订单
 * @author: jiafr 
 * @since: 2018年9月27日 下午8:37:44 
 * @history:
 */
public class XN629050 extends AProcessor {
    private IGroupAdoptOrderAO groupAdoptOrderAO = SpringContextHolder
        .getBean(IGroupAdoptOrderAO.class);

    private XN629050Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return groupAdoptOrderAO.addGroupAdoptOrder(req);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629050Req.class);
        ObjValidater.validateReq(req);
    }
}
