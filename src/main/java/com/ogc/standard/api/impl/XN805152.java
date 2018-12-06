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
import com.ogc.standard.dto.req.XN805152Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 审核关系
 * @author: silver 
 * @since: Dec 6, 2018 4:33:15 PM 
 * @history:
 */
public class XN805152 extends AProcessor {

    private IUserRelationAO userRelationAO = SpringContextHolder
        .getBean(IUserRelationAO.class);

    private XN805152Req req;

    @Override
    public Object doBusiness() throws BizException {
        userRelationAO.approveUser(req.getCode(), req.getUserId(),
            req.getApproveResult(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805152Req.class);
        ObjValidater.validateReq(req);
    }

}
