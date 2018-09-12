package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IBankcardAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN802020Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 绑定银行卡
 * @author: lei 
 * @since: 2018年9月11日 下午5:36:09 
 * @history:
 */
public class XN802020 extends AProcessor {
    private IBankcardAO bankCardAO = SpringContextHolder
        .getBean(IBankcardAO.class);

    private XN802020Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return bankCardAO.addBankcard(req);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802020Req.class);
        StringValidater.validateBlank(req.getSystemCode(),
            req.getBankcardNumber(), req.getBankCode(), req.getBankName(),
            req.getUserId(), req.getRealName(), req.getType());

    }
}
