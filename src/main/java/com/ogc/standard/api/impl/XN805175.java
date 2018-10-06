package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAddressAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Address;
import com.ogc.standard.dto.req.XN805175Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 收件地址查询
 * @author: xieyj 
 * @since: 2015年8月19日 下午7:48:10 
 * @history:
 */
public class XN805175 extends AProcessor {
    private IAddressAO addressAO = SpringContextHolder
        .getBean(IAddressAO.class);

    private XN805175Req req = null;

    /** 
     * @see com.xnjr.cpzc.service.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        Address condition = new Address();
        condition.setUserId(req.getUserId());
        condition.setIsDefault(req.getIsDefault());
        return addressAO.queryAddressList(condition);
    }

    /** 
     * @see com.xnjr.cpzc.service.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operater)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805175Req.class);
        StringValidater.validateBlank(req.getUserId());
    }
}
