package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAddressAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Address;
import com.ogc.standard.dto.req.XN805170Req;
import com.ogc.standard.dto.res.PKCodeRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 收件地址新增
 * @author: xieyj 
 * @since: 2015年8月19日 下午7:48:10 
 * @history:
 */
public class XN805170 extends AProcessor {
    private IAddressAO addressAO = SpringContextHolder
        .getBean(IAddressAO.class);

    private XN805170Req req = null;

    /**
     * @see com.xnjr.mall.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        Address data = new Address();
        data.setUserId(req.getUserId());
        data.setAddressee(req.getAddressee());
        data.setMobile(req.getMobile());
        data.setProvince(req.getProvince());
        data.setCity(req.getCity());
        data.setDistrict(req.getDistrict());
        data.setDetailAddress(req.getDetailAddress());
        data.setIsDefault(req.getIsDefault());
        return new PKCodeRes(addressAO.addAddress(data));
    }

    /**
     * @see com.xnjr.mall.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805170Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getAddressee(),
            req.getMobile(), req.getProvince(), req.getCity(),
            req.getDistrict(), req.getDetailAddress());
    }
}
