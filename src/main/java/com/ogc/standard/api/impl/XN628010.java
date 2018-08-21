package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ITeamAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN628010Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 设置权重(oss)
 * @author: silver 
 * @since: 2018年8月21日 下午4:58:42 
 * @history:
 */
public class XN628010 extends AProcessor {
    private ITeamAO teamAO = SpringContextHolder.getBean(ITeamAO.class);

    private XN628010Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        teamAO.editTeamWeight(req.getCode(),
            StringValidater.toDouble(req.getWeight()), req.getUpdater());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN628010Req.class);
        ObjValidater.validateReq(req);
    }

}
