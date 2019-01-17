/**
 * @Title XN629700.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 上午10:05:50 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICommodityAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629709Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 列表查商品发货地/产地
 * @author: silver 
 * @since: Jan 17, 2019 10:12:27 AM 
 * @history:
 */
public class XN629709 extends AProcessor {

    private ICommodityAO commodityAO = SpringContextHolder
        .getBean(ICommodityAO.class);

    private XN629709Req req;

    @Override
    public Object doBusiness() throws BizException {
        return commodityAO.queryPlaceList(req.getPlaceType());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629709Req.class);
        ObjValidater.validateReq(req);
    }

}
