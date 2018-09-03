package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAdsAO;
import com.ogc.standard.ao.ITradeOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN625229Req;
import com.ogc.standard.dto.req.XN625255Req;
import com.ogc.standard.dto.res.XN625255Res;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

import java.math.BigDecimal;

/**
 * Created by tianlei on 2017/十一月/22.
 */
public class XN625229 extends AProcessor {

    private IAdsAO adsAO = SpringContextHolder
            .getBean(IAdsAO.class);
    XN625229Req req;

    @Override
    public Object doBusiness() throws BizException {

        return this.adsAO.frontSearchAdsByNickName(req.getNickName());

    }

    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {

        req = JsonUtil.json2Bean(inputparams, XN625229Req.class);
        ObjValidater.validateReq(req);
    }
}
