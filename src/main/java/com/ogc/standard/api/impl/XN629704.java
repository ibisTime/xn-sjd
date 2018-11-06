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
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN629704Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 上架商品
 * @author: taojian 
 * @since: 2018年11月6日 上午10:05:50 
 * @history:
 */
public class XN629704 extends AProcessor {

    private ICommodityAO commodityAO = SpringContextHolder
        .getBean(ICommodityAO.class);

    private XN629704Req req;

    @Override
    public Object doBusiness() throws BizException {
        commodityAO.putOnShelf(req.getCode(), req.getLocation(),
            StringValidater.toLong(req.getOrderNo()), req.getUpdater(),
            req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629704Req.class);
        ObjValidater.validateReq(req);
    }

}
