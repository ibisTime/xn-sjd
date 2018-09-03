/**
 * @Title XN802170.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2018年2月1日 下午8:08:46 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IPersonalAddressAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.PersonalAddress;
import com.ogc.standard.dto.req.XN802015Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 分页查询用户取现地址
 * @author: haiqingzheng 
 * @since: 2018年2月1日 下午8:08:46 
 * @history:
 */
public class XN802015 extends AProcessor {
    private IPersonalAddressAO personalAddressAO = SpringContextHolder
        .getBean(IPersonalAddressAO.class);

    private XN802015Req req = null;

    /** 
     * @see com.ogc.standard.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        PersonalAddress condition = new PersonalAddress();
        condition.setUserId(req.getUserId());
        condition.setSymbol(req.getSymbol());
        condition.setOrder("create_datetime", "desc");
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return personalAddressAO.queryPersonalAddressPage(start, limit,
            condition);
    }

    /** 
     * @see com.ogc.standard.api.IProcessor#doCheck(java.lang.String, java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802015Req.class);
        ObjValidater.validateReq(req);
    }

}
