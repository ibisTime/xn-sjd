/**
 * @Title XN629730.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月8日 下午8:15:26 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICommodityOrderDetailAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629730Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 发货
 * @author: taojian 
 * @since: 2018年11月8日 下午8:15:26 
 * @history:
 */
public class XN629730 extends AProcessor {

    private ICommodityOrderDetailAO commodityOrderDetailAO = SpringContextHolder
        .getBean(ICommodityOrderDetailAO.class);

    private XN629730Req req;

    @Override
    public Object doBusiness() throws BizException {
        commodityOrderDetailAO.delive(req.getOrderCode(), req.getShopCode(),
            req.getLogisticsCompany(), req.getLogisticsNumber(),
            req.getDeliver());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629730Req.class);
        ObjValidater.validateReq(req);
    }

}
