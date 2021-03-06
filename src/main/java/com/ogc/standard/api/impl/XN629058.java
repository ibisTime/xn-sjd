package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IGroupAdoptOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629058Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 查询订单抵扣金额
 * @author: xieyj 
 * @since: 2018年10月2日 下午4:40:48 
 * @history:
 */
public class XN629058 extends AProcessor {
    private IGroupAdoptOrderAO groupAdoptOrderAO = SpringContextHolder
        .getBean(IGroupAdoptOrderAO.class);

    private XN629058Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return groupAdoptOrderAO.getOrderDkAmount(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629058Req.class);
        ObjValidater.validateReq(req);
    }
}
