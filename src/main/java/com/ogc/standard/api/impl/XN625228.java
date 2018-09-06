package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAdsAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN625228Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 根据昵称查询广告
 * @author: taojian 
 * @since: 2018年9月5日 下午10:57:16 
 * @history:
 */
public class XN625228 extends AProcessor {

    private IAdsAO adsAO = SpringContextHolder.getBean(IAdsAO.class);

    XN625228Req req;

    @Override
    public Object doBusiness() throws BizException {

        return this.adsAO.frontSearchAdsByNickName(req.getNickName());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {

        req = JsonUtil.json2Bean(inputparams, XN625228Req.class);
        ObjValidater.validateReq(req);
    }
}
