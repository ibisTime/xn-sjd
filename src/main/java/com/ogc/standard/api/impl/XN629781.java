/**
 * @Title XN629780.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月8日 下午2:55:58 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ISessionAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629781Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 回复消息
 * @author: taojian 
 * @since: 2018年11月8日 下午2:55:58 
 * @history:
 */
public class XN629781 extends AProcessor {

    private ISessionAO sessionAO = SpringContextHolder
        .getBean(ISessionAO.class);

    private XN629781Req req;

    @Override
    public Object doBusiness() throws BizException {
        sessionAO.replay(req.getCode(), req.getUserId(), req.getContent());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629781Req.class);
        ObjValidater.validateReq(req);
    }

}
