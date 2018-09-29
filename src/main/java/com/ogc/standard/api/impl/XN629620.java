package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IMaintainProjectAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629620Req;
import com.ogc.standard.dto.res.PKCodeRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 添加养护项目
 * @author: silver 
 * @since: 2018年9月29日 上午10:49:25 
 * @history:
 */
public class XN629620 extends AProcessor {
    private IMaintainProjectAO maintainProjectAO = SpringContextHolder
        .getBean(IMaintainProjectAO.class);

    private XN629620Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(maintainProjectAO.addMaintainProject(
            req.getMaintainId(), req.getProjectName(), req.getDescription(),
            req.getUpdater(), req.getRemark()));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629620Req.class);
        ObjValidater.validateReq(req);
    }
}
