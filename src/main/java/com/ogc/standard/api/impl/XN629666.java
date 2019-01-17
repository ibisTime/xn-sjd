package com.ogc.standard.api.impl;

import com.ogc.standard.ao.INotifyUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629666Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 详细查审核通知人
 * @author: silver 
 * @since: Jan 17, 2019 4:00:32 PM 
 * @history:
 */
public class XN629666 extends AProcessor {
    private INotifyUserAO notifyUserAO = SpringContextHolder
        .getBean(INotifyUserAO.class);

    private XN629666Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return notifyUserAO.getNotifyUser(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629666Req.class);
        ObjValidater.validateReq(req);
    }
}
