package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IGroupAdoptOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629051Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 非第一人下单集体认养订单
 * @author: jiafr 
 * @since: 2018年9月27日 下午8:37:44 
 * @history:
 */
public class XN629051 extends AProcessor {
    private IGroupAdoptOrderAO groupAdoptOrderAO = SpringContextHolder
        .getBean(IGroupAdoptOrderAO.class);

    private XN629051Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Integer quantity = Integer.valueOf(req.getQuantity());
        String code = groupAdoptOrderAO.unFirstAddGroupAdoptOrder(
            req.getIdentifyCode(), req.getUserId(), quantity);
        return code;
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629051Req.class);
        ObjValidater.validateReq(req);
    }
}
