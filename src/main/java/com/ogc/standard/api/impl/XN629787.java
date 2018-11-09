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
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.Session;
import com.ogc.standard.dto.req.XN629787Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 列表查会话
 * @author: taojian 
 * @since: 2018年11月8日 下午2:55:58 
 * @history:
 */
public class XN629787 extends AProcessor {

    private ISessionAO sessionAO = SpringContextHolder
        .getBean(ISessionAO.class);

    private XN629787Req req;

    @Override
    public Object doBusiness() throws BizException {
        Session condition = new Session();
        condition.setType(req.getType());
        condition.setUser1(req.getUser1());
        condition.setUser2(req.getUser2());
        condition.setCreateDatetimeStart(DateUtil.getFrontDate(
            req.getCreateDatetimeStart(), false));
        condition.setCreateDatetimeEnd(DateUtil.getFrontDate(
            req.getCreateDatetimeEnd(), true));
        return sessionAO.querySessionList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629787Req.class);
        ObjValidater.validateReq(req);
    }

}
