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
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.UserRelation;
import com.ogc.standard.dto.req.XN805155Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 分页查询用户间关系
 * @author: taojian 
 * @since: 2018年9月14日 下午12:36:40 
 * @history:
 */
public class XN805155 extends AProcessor {

    private IUserRelationAO userRelationAO = SpringContextHolder
        .getBean(IUserRelationAO.class);

    private XN805155Req req;

    @Override
    public Object doBusiness() throws BizException {
        UserRelation condition = new UserRelation();
        condition.setUserId(req.getUserId());
        condition.setToUser(req.getToUser());
        condition.setType(req.getType());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return userRelationAO.queryUserRelationPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805155Req.class);
        ObjValidater.validateReq(req);
    }

}
