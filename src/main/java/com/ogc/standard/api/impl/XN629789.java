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
import com.ogc.standard.dto.req.XN629789Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 清空未读消息
 * @author: silver 
 * @since: Dec 6, 2018 5:03:38 PM 
 * @history:
 */
public class XN629789 extends AProcessor {

    private ISessionAO sessionAO = SpringContextHolder
        .getBean(ISessionAO.class);

    private XN629789Req req;

    @Override
    public Object doBusiness() throws BizException {
        sessionAO.clearUserUnreadSum(req.getUser1(), req.getUser2(),
            req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629789Req.class);
        ObjValidater.validateReq(req);
    }

}
