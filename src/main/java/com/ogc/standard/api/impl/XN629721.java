/**
 * @Title XN629720.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月7日 上午10:34:19 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICommodityOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629721Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 支付订单
 * @author: taojian 
 * @since: 2018年11月7日 上午10:34:19 
 * @history:
 */
public class XN629721 extends AProcessor {

    private ICommodityOrderAO commodityOrderAO = SpringContextHolder
        .getBean(ICommodityOrderAO.class);

    private XN629721Req req;

    @Override
    public Object doBusiness() throws BizException {

        return commodityOrderAO.toPayCommodityOrder(req);

    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629721Req.class);
        ObjValidater.validateReq(req);
    }

}
