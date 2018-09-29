package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IMaintainProjectAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.MaintainProject;
import com.ogc.standard.dto.req.XN629627Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 列表查询养护项目
 * @author: silver 
 * @since: 2018年9月29日 上午10:49:25 
 * @history:
 */
public class XN629627 extends AProcessor {
    private IMaintainProjectAO maintainProjectAO = SpringContextHolder
        .getBean(IMaintainProjectAO.class);

    private XN629627Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        MaintainProject condition = new MaintainProject();
        condition.setMaintainId(req.getMaintainId());
        condition.setProjectName(req.getProjectName());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IMaintainProjectAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return maintainProjectAO.queryMaintainProjectList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629627Req.class);
        ObjValidater.validateReq(req);
    }
}
