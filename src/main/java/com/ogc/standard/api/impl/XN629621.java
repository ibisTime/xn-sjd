package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IMaintainProjectAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629621Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 删除养护项目
 * @author: silver 
 * @since: 2018年9月29日 上午10:49:25 
 * @history:
 */
public class XN629621 extends AProcessor {
    private IMaintainProjectAO maintainProjectAO = SpringContextHolder
        .getBean(IMaintainProjectAO.class);

    private XN629621Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        maintainProjectAO.dropMaintainProject(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629621Req.class);
        ObjValidater.validateReq(req);
    }
}
