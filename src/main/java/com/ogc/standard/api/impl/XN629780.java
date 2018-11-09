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
import com.ogc.standard.dto.req.XN629780Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 发送消息（front）
 * @author: taojian 
 * @since: 2018年11月8日 下午2:55:58 
 * @history:
 */
public class XN629780 extends AProcessor {

    private ISessionAO sessionAO = SpringContextHolder
        .getBean(ISessionAO.class);

    private XN629780Req req;

    @Override
    public Object doBusiness() throws BizException {
        sessionAO.send(req.getUser1(), req.getUser2(), req.getContent(),
            req.getType());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629780Req.class);
        ObjValidater.validateReq(req);
    }

}
