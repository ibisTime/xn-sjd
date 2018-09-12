/**
 * @Title XN802580.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年9月11日 下午8:31:50 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IBtcWAddressAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN802580Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 导入btc归集地址
 * @author: taojian 
 * @since: 2018年9月11日 下午8:31:50 
 * @history:
 */
public class XN802580 extends AProcessor {
    private IBtcWAddressAO btcWAddressAO = SpringContextHolder
        .getBean(IBtcWAddressAO.class);

    private XN802580Req req;

    @Override
    public Object doBusiness() throws BizException {
        btcWAddressAO.importWAddress(req.getAddress());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802580Req.class);
        ObjValidater.validateReq(req);

    }

}
