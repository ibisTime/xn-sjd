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
import com.ogc.standard.dto.req.XN629802Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 根据支付组号查订单明细
 * @author: silver 
 * @since: Dec 5, 2018 3:01:24 PM 
 * @history:
 */
public class XN629802 extends AProcessor {

    private ICommodityOrderAO commodityOrderAO = SpringContextHolder
        .getBean(ICommodityOrderAO.class);

    private XN629802Req req;

    @Override
    public Object doBusiness() throws BizException {
        return commodityOrderAO.getInfoByPayGroup(req.getPayGroupCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629802Req.class);
        ObjValidater.validateReq(req);
    }

}
