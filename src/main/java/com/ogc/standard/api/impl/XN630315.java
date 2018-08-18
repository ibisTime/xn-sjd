/**
 * @Title XN630315.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author dl  
 * @date 2018年8月18日 上午10:46:32 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IDepartmentAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Department;
import com.ogc.standard.dto.req.XN630315Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 分页查询部门
 * @author: tao 
 * @since: 2018年8月18日 上午10:46:32 
 * @history:
 */
public class XN630315 extends AProcessor {
    private IDepartmentAO departmentAO = SpringContextHolder
        .getBean(IDepartmentAO.class);

    private XN630315Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Department condition = new Department();
        condition.setCompanyCode(req.getCompanyCode());
        condition.setLeader(req.getLeader());
        condition.setLeaderMobile(req.getLeaderMobile());
        condition.setName(req.getName());
        condition.setParentCode(req.getParentCode());
        condition.setUpdater(req.getUpdater());
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IDepartmentAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return departmentAO.queryDepartmentPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630315Req.class);
        ObjValidater.validateReq(req);
    }

}
