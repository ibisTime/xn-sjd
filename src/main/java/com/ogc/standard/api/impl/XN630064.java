package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICompanyAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN630064Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 完善公司信息
 * @author: xieyj 
 * @since: 2018年10月4日 下午1:47:59 
 * @history:
 */
public class XN630064 extends AProcessor {
    private ICompanyAO companyAO = SpringContextHolder
        .getBean(ICompanyAO.class);

    private XN630064Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        companyAO.completeCompanyOwner(req.getUserId(),
            req.getCertificateTemplate(), req.getContractTemplate());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630064Req.class);
        ObjValidater.validateReq(req);
    }
}
