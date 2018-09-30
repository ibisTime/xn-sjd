package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICompanyAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN630300Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 填写公司资料
 * @author: jiafr 
 * @since: 2018年9月30日 下午1:26:19 
 * @history:
 */
public class XN630300 extends AProcessor {
    private ICompanyAO companyAO = SpringContextHolder
        .getBean(ICompanyAO.class);

    private XN630300Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return companyAO.addCompany(req);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630300Req.class);
        ObjValidater.validateReq(req);

    }

}
