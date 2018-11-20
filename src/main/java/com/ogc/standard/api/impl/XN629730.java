/**
 * @Title XN629750.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月7日 下午5:08:26 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICommodityOrderDetailAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629730Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.dto.res.PKCodeRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 订单评论
 * @author: silver 
 * @since: Nov 20, 2018 3:59:04 PM 
 * @history:
 */
public class XN629730 extends AProcessor {

    private ICommodityOrderDetailAO commodityOrderDetailAO = SpringContextHolder
        .getBean(ICommodityOrderDetailAO.class);

    private XN629730Req req;

    @Override
    public Object doBusiness() throws BizException {
        String commentCode = commodityOrderDetailAO.comment(req.getCode(),
            req.getUserId(), req.getContent());
        return new PKCodeRes(commentCode);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629730Req.class);
        ObjValidater.validateReq(req);
    }

}
