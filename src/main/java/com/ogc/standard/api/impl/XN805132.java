/**
 * @Title XN805130.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年9月13日 下午2:54:34 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IUserFieldApproveAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN805132Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 审核
 * @author: taojian 
 * @since: 2018年9月13日 下午2:54:34 
 * @history:
 */
public class XN805132 extends AProcessor {

    private IUserFieldApproveAO userFieldApproveAO = SpringContextHolder
        .getBean(IUserFieldApproveAO.class);

    private XN805132Req req;

    @Override
    public Object doBusiness() throws BizException {
        userFieldApproveAO.approve(StringValidater.toLong(req.getId()),
            req.getApproveResult(), req.getApproveUser(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805132Req.class);
        ObjValidater.validateReq(req);
    }

}
