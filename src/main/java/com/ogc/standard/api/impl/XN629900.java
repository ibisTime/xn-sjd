package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IBizLogAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629900Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 629900
 * @author: silver 
 * @since: Oct 5, 2018 3:15:36 PM 
 * @history:
 */
public class XN629900 extends AProcessor {
    private IBizLogAO bizLogAO = SpringContextHolder.getBean(IBizLogAO.class);

    private XN629900Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return bizLogAO.weekTpp(req.getUserId(), req.getToUserId());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629900Req.class);
        ObjValidater.validateReq(req);
    }
}
