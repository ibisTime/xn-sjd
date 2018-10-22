package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAdoptOrderTreeAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629904Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 产权方已认养的总值
 * @author: silver 
 * @since: Oct 22, 2018 3:52:28 PM 
 * @history:
 */
public class XN629904 extends AProcessor {
    private IAdoptOrderTreeAO adoptOrderTreeAO = SpringContextHolder
        .getBean(IAdoptOrderTreeAO.class);

    private XN629904Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return adoptOrderTreeAO.getTotalAmount(req.getUserId(),
            req.getStatusList());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629904Req.class);
        ObjValidater.validateReq(req);
    }
}
