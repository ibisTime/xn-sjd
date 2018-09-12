package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IBankcardAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN802022Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 修改银行卡
 * @author: lei 
 * @since: 2018年9月11日 下午5:36:23 
 * @history:
 */
public class XN802022 extends AProcessor {
    private IBankcardAO bankCardAO = SpringContextHolder
        .getBean(IBankcardAO.class);

    private XN802022Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        bankCardAO.editBankcard(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802022Req.class);
        StringValidater.validateBlank(req.getCode(), req.getBankcardNumber(),
            req.getBankCode(), req.getBankName());

    }
}
