/**
 * @Title XN630311.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author dl  
 * @date 2018年8月18日 上午10:36:27 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IDepartmentAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN630311Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 删除部门
 * @author: tao 
 * @since: 2018年8月18日 上午10:36:27 
 * @history:
 */
public class XN630311 extends AProcessor {

    private IDepartmentAO departmentAO = SpringContextHolder
        .getBean(IDepartmentAO.class);

    private XN630311Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        departmentAO.dropDepartment(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630311Req.class);
        ObjValidater.validateReq(req);
    }

}
