/**
 * @Title XN805300.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author dl  
 * @date 2018年8月22日 下午3:07:39 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ISmsAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN805300Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 保存消息（草稿）
 * @author: dl 
 * @since: 2018年8月22日 下午3:07:39 
 * @history:
 */
public class XN805300 extends AProcessor {

    private ISmsAO smsAO = SpringContextHolder.getBean(ISmsAO.class);

    private XN805300Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return smsAO.addSms(req);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805300Req.class);
        ObjValidater.validateReq(req);
    }

}
