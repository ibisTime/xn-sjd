package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICompanyAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN630068Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 查询用户公司信息
 * @author: silver 
 * @since: Oct 8, 2018 9:28:57 PM 
 * @history:
 */
public class XN630068 extends AProcessor {

    private ICompanyAO companyAO = SpringContextHolder
        .getBean(ICompanyAO.class);

    private XN630068Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return companyAO.getCompanyByUser(req.getUserId());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630068Req.class);
        StringValidater.validateBlank(req.getUserId());
    }

}
