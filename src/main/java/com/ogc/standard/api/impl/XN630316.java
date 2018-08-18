/**
 * @Title XN630316.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author dl  
 * @date 2018年8月18日 上午10:53:42 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IDepartmentAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.Department;
import com.ogc.standard.dto.req.XN630316Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 列表查询部门
 * @author: tao 
 * @since: 2018年8月18日 上午10:53:42 
 * @history:
 */
public class XN630316 extends AProcessor {

    private IDepartmentAO departmentAO = SpringContextHolder
        .getBean(IDepartmentAO.class);

    private XN630316Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        Department condition = new Department();
        condition.setCompanyCode(req.getCompanyCode());
        condition.setLeader(req.getLeader());
        condition.setLeaderMobile(req.getLeaderMobile());
        condition.setName(req.getName());
        condition.setParentCode(req.getParentCode());
        condition.setUpdater(req.getUpdater());
        return departmentAO.queryDepartmentList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630316Req.class);
        ObjValidater.validateReq(req);
    }

}
