package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.User;
import com.ogc.standard.dto.req.XN805121Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 根据userId获取用户信息
 * @author: dl 
 * @since: 2018年8月21日 下午2:51:59 
 * @history:
 */
public class XN805121 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805121Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        User user = userAO.doGetUser(req.getUserId());

        return user;

    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805121Req.class);
        ObjValidater.validateReq(req);
    }

}
