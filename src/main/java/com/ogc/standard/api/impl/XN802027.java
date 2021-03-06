package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IBankcardAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN802027Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 详情查询银行卡
 * @author: lei 
 * @since: 2018年9月11日 下午5:39:49 
 * @history:
 */
public class XN802027 extends AProcessor {
    private IBankcardAO bankCardAO = SpringContextHolder
        .getBean(IBankcardAO.class);

    private XN802027Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return bankCardAO.getBankcard(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802027Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
