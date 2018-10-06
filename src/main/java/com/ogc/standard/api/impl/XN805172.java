package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAddressAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Address;
import com.ogc.standard.dto.req.XN805172Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 收件地址 修改
 * @author: xieyj 
 * @since: 2015年8月19日 下午7:48:10 
 * @history:
 */
public class XN805172 extends AProcessor {
    private IAddressAO addressAO = SpringContextHolder
        .getBean(IAddressAO.class);

    private XN805172Req req = null;

    /** 
     * @see com.xnjr.cpzc.service.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        Address data = new Address();
        data.setCode(req.getCode());
        data.setAddressee(req.getAddressee());
        data.setMobile(req.getMobile());
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());
        data.setDistrict(req.getDistrict());
        data.setDetailAddress(req.getDetailAddress());
        data.setIsDefault(req.getIsDefault());
        int count = addressAO.editAddress(data);
        return new BooleanRes(count > 0 ? true : false);
    }

    /** 
     * @see com.xnjr.cpzc.service.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operater)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805172Req.class);
        StringValidater.validateBlank(req.getCode(), req.getAddressee(),
            req.getMobile(), req.getProvince(), req.getCity(),
            req.getDistrict(), req.getDetailAddress());
    }
}
