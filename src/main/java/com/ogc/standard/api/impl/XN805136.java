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
import com.ogc.standard.dto.req.XN805136Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 详情查询审核信息
 * @author: taojian 
 * @since: 2018年9月13日 下午2:54:34 
 * @history:
 */
public class XN805136 extends AProcessor {

    private IUserFieldApproveAO userFieldApproveAO = SpringContextHolder
        .getBean(IUserFieldApproveAO.class);

    private XN805136Req req;

    @Override
    public Object doBusiness() throws BizException {

        return userFieldApproveAO
            .getUserFieldApprove(StringValidater.toLong(req.getId()));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805136Req.class);
        ObjValidater.validateReq(req);
    }

}
