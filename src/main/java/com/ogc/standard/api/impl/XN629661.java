package com.ogc.standard.api.impl;

import com.ogc.standard.ao.INotifyUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629661Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 添加审核通知人
 * @author: silver 
 * @since: Jan 17, 2019 3:45:34 PM 
 * @history:
 */
public class XN629661 extends AProcessor {
    private INotifyUserAO notifyUserAO = SpringContextHolder
        .getBean(INotifyUserAO.class);

    private XN629661Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        notifyUserAO.dropNotifyUser(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629661Req.class);
        ObjValidater.validateReq(req);
    }
}
