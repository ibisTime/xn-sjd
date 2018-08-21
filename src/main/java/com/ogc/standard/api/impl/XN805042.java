package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN805042Req;
import com.ogc.standard.dto.res.XN805042Res;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 管理端代注册
 * @author: myb858 
 * @since: 2015年11月10日 下午12:59:10 
 * @history:
 */
public class XN805042 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805042Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new XN805042Res(userAO.doAddUser(req));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805042Req.class);
        ObjValidater.validateReq(req);
    }
}
