/**
 * @Title XN805089.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年9月12日 下午10:46:17 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN805089Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 关闭谷歌验证
 * @author: taojian 
 * @since: 2018年9月12日 下午10:46:17 
 * @history:
 */
public class XN805089 extends AProcessor {

    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805089Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.closeGoogleAuth(req.getUserId(), req.getSmsCaptcha(),
            req.getGoogleCaptcha());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805089Req.class);
        ObjValidater.validateReq(req);
    }

}
