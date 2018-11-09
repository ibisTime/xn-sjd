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
import com.ogc.standard.dto.req.XN629736Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 详情查订单明细
 * @author: taojian 
 * @since: 2018年11月8日 下午8:15:26 
 * @history:
 */
public class XN629736 extends AProcessor {

    private ICommodityOrderDetailAO commodityOrderDetailAO = SpringContextHolder
        .getBean(ICommodityOrderDetailAO.class);

    private XN629736Req req;

    @Override
    public Object doBusiness() throws BizException {
        return commodityOrderDetailAO.getCommodityOrderDetail(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629736Req.class);
        ObjValidater.validateReq(req);
    }

}
