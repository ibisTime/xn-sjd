package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAdoptOrderTreeAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629201Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分享(认养权)
 * @author: jiafr 
 * @since: 2018年9月28日 下午2:33:14 
 * @history:
 */
public class XN629201 extends AProcessor {
    private IAdoptOrderTreeAO adoptOrderTreeAO = SpringContextHolder
        .getBean(IAdoptOrderTreeAO.class);

    private XN629201Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        adoptOrderTreeAO
            .share(req.getCode(), req.getUserId(), req.getChannel());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629201Req.class);
        ObjValidater.validateReq(req);
    }
}
