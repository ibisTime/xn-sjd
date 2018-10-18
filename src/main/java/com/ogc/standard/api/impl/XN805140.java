/**
 * @Title XN805140.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author dl  
 * @date 2018年8月20日 下午5:57:16 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ISignLogAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN805140Req;
import com.ogc.standard.dto.res.XN805140Res;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 签到
 * @author: dl 
 * @since: 2018年8月20日 下午5:57:16 
 * @history:
 */
public class XN805140 extends AProcessor {
    private ISignLogAO signLogAO = SpringContextHolder
        .getBean(ISignLogAO.class);

    private XN805140Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        signLogAO.addSignLog(req);

        XN805140Res res = signLogAO.doAssignSignTPP(req.getUserId());

        return res;
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805140Req.class);
        ObjValidater.validateReq(req);
    }

}
