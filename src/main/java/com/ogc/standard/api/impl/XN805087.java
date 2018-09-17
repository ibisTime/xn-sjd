/**
 * @Title XN805087.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年9月12日 下午7:56:51 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN805087Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 获取腾讯云签名
 * @author: taojian 
 * @since: 2018年9月12日 下午7:56:51 
 * @history:
 */
public class XN805087 extends AProcessor {

    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805087Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return userAO.getTencentSign(req.getUserId());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805087Req.class);
        ObjValidater.validateReq(req);

    }

}
