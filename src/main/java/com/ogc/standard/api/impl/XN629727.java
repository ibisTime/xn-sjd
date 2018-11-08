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
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.CommodityOrder;
import com.ogc.standard.dto.req.XN629727Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 分页查订单
 * @author: taojian 
 * @since: 2018年11月7日 上午10:34:19 
 * @history:
 */
public class XN629727 extends AProcessor {

    private ICommodityOrderAO commodityOrderAO = SpringContextHolder
        .getBean(ICommodityOrderAO.class);

    private XN629727Req req;

    @Override
    public Object doBusiness() throws BizException {
        CommodityOrder condition = new CommodityOrder();
        condition.setApplyUser(req.getApplyUser());
        condition.setExpressType(req.getExpressType());
        condition.setPayType(req.getPayType());
        condition.setStatus(req.getStatus());
        condition.setApplyDatetimeStart(DateUtil.getFrontDate(
            req.getApplyDatetiemStart(), false));
        condition.setApplyDatetimeEnd(DateUtil.getFrontDate(
            req.getApplyDatetimeEnd(), true));
        return commodityOrderAO.queryOrderList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629727Req.class);
        ObjValidater.validateReq(req);
    }

}
