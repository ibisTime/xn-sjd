package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAdoptOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629046Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 详情查询个人定向捐赠认养订单
 * @author: jiafr 
 * @since: 2018年9月27日 下午8:02:50 
 * @history:
 */
public class XN629046 extends AProcessor {

    private IAdoptOrderAO adoptOrderAO = SpringContextHolder
        .getBean(IAdoptOrderAO.class);

    private XN629046Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return adoptOrderAO.getAdoptOrder(req.getCode(), req.getIsSettle());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629046Req.class);
        ObjValidater.validateReq(req);
    }
}
