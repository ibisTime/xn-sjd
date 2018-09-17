/**
 * @Title XN805150.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年9月14日 下午12:36:40 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IUserRelationAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN805151Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 解除关系
 * @author: taojian 
 * @since: 2018年9月14日 下午12:36:40 
 * @history:
 */
public class XN805151 extends AProcessor {

    private IUserRelationAO userRelationAO = SpringContextHolder
        .getBean(IUserRelationAO.class);

    private XN805151Req req;

    @Override
    public Object doBusiness() throws BizException {
        userRelationAO.unfollowUser(req.getUserId(), req.getToUser(),
            req.getType());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805151Req.class);
        ObjValidater.validateReq(req);
    }

}
