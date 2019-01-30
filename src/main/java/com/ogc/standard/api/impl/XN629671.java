package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IOfficialSealAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629671Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 删除添加公章
 * @author: silver 
 * @since: Jan 17, 2019 3:45:34 PM 
 * @history:
 */
public class XN629671 extends AProcessor {

    private IOfficialSealAO officialSealAO = SpringContextHolder
        .getBean(IOfficialSealAO.class);

    private XN629671Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        officialSealAO.dropOfficialSeal(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629671Req.class);
        ObjValidater.validateReq(req);
    }
}
