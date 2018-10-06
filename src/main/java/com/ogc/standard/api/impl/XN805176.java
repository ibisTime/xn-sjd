package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAddressAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Address;
import com.ogc.standard.dto.req.XN805176Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 收件地址详情
 * @author: xieyj 
 * @since: 2015年8月19日 下午7:48:10 
 * @history:
 */
public class XN805176 extends AProcessor {
    private IAddressAO addressAO = SpringContextHolder
        .getBean(IAddressAO.class);

    private XN805176Req req = null;

    /** 
     * @see com.xnjr.cpzc.service.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        Address condition = new Address();
        condition.setCode(req.getCode());
        return addressAO.getAddress(req.getCode());
    }

    /** 
     * @see com.xnjr.cpzc.service.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operater)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805176Req.class);
        StringValidater.validateBlank(req.getCode());
    }
}
