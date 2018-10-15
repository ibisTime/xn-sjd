/**
 * @Title XN805148.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author dl  
 * @date 2018年8月23日 下午9:00:15 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ISignLogAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN805148Req;
import com.ogc.standard.enums.ESignLogType;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 获得连续签到数
 * @author: dl 
 * @since: 2018年8月23日 下午9:00:15 
 * @history:
 */
public class XN805148 extends AProcessor {

    private ISignLogAO signLogAO = SpringContextHolder
        .getBean(ISignLogAO.class);

    private XN805148Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return signLogAO.keepCheckIn(req.getUserId(),
            ESignLogType.SIGN_IN.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805148Req.class);
        ObjValidater.validateReq(req);
    }

}
