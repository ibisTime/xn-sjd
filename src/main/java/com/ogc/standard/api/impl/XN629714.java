/**
 * @Title XN629710.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 上午11:15:12 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICommodityOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629714Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * @author: taojian 
 * @since: 2018年11月6日 上午11:15:12 
 * @history:
 */
public class XN629714 extends AProcessor {

    private ICommodityOrderAO commodityOrderAO = SpringContextHolder
        .getBean(ICommodityOrderAO.class);

    private XN629714Req req;

    @Override
    public Object doBusiness() throws BizException {
        return commodityOrderAO.toPayGroupCommodityOrder(req);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629714Req.class);
        ObjValidater.validateReq(req);
    }

}
