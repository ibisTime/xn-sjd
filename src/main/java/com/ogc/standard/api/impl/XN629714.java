/**
 * @Title XN629710.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 上午11:15:12 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICartAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629714Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 根据店铺删购物车
 * @author: silver 
 * @since: Nov 15, 2018 7:04:13 PM 
 * @history:
 */
public class XN629714 extends AProcessor {

    private ICartAO cartAO = SpringContextHolder.getBean(ICartAO.class);

    private XN629714Req req;

    @Override
    public Object doBusiness() throws BizException {
        cartAO.dropByShop(req.getShopCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629714Req.class);
        ObjValidater.validateReq(req);
    }

}
