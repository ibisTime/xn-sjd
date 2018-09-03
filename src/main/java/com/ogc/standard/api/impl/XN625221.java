package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAdsAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN625220Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * Created by tianlei on 2017/十一月/15.
 */
public class XN625221 extends AProcessor {

    // 草稿发布为广告
    private IAdsAO adsAO = SpringContextHolder.getBean(IAdsAO.class);

    XN625220Req req;

    @Override
    public Object doBusiness() throws BizException {

        throw new BizException("xn000", "请调用625220");
        // this.adsAO.draftPublish(req);
        // return new BooleanRes(true);

    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {

        req = JsonUtil.json2Bean(inputparams, XN625220Req.class);
        req.setUserId(operator);
        ObjValidater.validateReq(req);

    }
}
