package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAdsAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN625220Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 保存草稿或直接发布
 * @author: taojian 
 * @since: 2018年9月4日 下午3:58:30 
 * @history:
 */
public class XN625220 extends AProcessor {

    private IAdsAO adsAO = SpringContextHolder.getBean(IAdsAO.class);

    private XN625220Req req;

    @Override
    public Object doBusiness() throws BizException {
        // 并发问题
        synchronized (IAdsAO.class) {
            this.adsAO.publishAds(req);
            return new BooleanRes(true);
        }

    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN625220Req.class);
        req.setUserId(operator);
        ObjValidater.validateReq(req);
    }
}
