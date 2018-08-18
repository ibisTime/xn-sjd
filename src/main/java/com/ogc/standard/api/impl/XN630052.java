package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ISYSUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN630052Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 *  重置手机 （系统用户）
 * @author: clockorange 
 * @since: Jul 17, 2018 11:54:25 AM 
 * @history:
 */

public class XN630052 extends AProcessor {

    private ISYSUserAO userAO = SpringContextHolder.getBean(ISYSUserAO.class);

    private XN630052Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doResetMoblie(req.getUserId(), req.getKind(), req.getNewMobile(),
            req.getSmsCaptcha());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630052Req.class);
        ObjValidater.validateReq(req);
    }

}
