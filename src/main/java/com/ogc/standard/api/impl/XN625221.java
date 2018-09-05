package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAdsAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN625221Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 广告修改
 * @author: taojian 
 * @since: 2018年9月5日 下午4:34:26 
 * @history:
 */
public class XN625221 extends AProcessor {

    private IAdsAO adsAO = SpringContextHolder.getBean(IAdsAO.class);

    private XN625221Req req;

    @Override
    public Object doBusiness() throws BizException {

        this.adsAO.editAds(req);
        return new BooleanRes(true);

    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {

        req = JsonUtil.json2Bean(inputparams, XN625221Req.class);
        req.setUserId(operator);
        ObjValidater.validateReq(req);

    }
}
