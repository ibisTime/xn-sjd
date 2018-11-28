package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IJourAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629905Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 养护产权收益
 * @author: silver 
 * @since: Oct 22, 2018 3:52:28 PM 
 * @history:
 */
public class XN629905 extends AProcessor {
    private IJourAO jourAO = SpringContextHolder.getBean(IJourAO.class);

    private XN629905Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return jourAO.getTotalBenefitAmount(req.getUserId(),
            req.getAccountType(), req.getDateStart(), req.getDateEnd());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629905Req.class);
        ObjValidater.validateReq(req);
    }
}
