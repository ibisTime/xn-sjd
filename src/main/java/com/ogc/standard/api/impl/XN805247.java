/**
 * @Title XN805247.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author dl  
 * @date 2018年8月20日 下午1:36:41 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IBlacklistAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN805247Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 详情查询黑名单
 * @author: dl 
 * @since: 2018年8月20日 下午1:36:41 
 * @history:
 */
public class XN805247 extends AProcessor {
    private IBlacklistAO blacklistAO = SpringContextHolder
        .getBean(IBlacklistAO.class);

    private XN805247Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return blacklistAO.getBlacklist(StringValidater.toLong(req.getId()));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805247Req.class);
        ObjValidater.validateReq(req);

    }

}
