/**
 * @Title XN629730.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月8日 下午8:15:26 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.ICommodityOrderDetailAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.CommodityOrderDetail;
import com.ogc.standard.dto.req.XN629737Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 列表查订单明细
 * @author: taojian 
 * @since: 2018年11月8日 下午8:15:26 
 * @history:
 */
public class XN629737 extends AProcessor {

    private ICommodityOrderDetailAO commodityOrderDetailAO = SpringContextHolder
        .getBean(ICommodityOrderDetailAO.class);

    private XN629737Req req;

    @Override
    public Object doBusiness() throws BizException {
        CommodityOrderDetail condition = new CommodityOrderDetail();
        condition.setOrderCode(req.getOrderCode());
        condition.setShopCode(req.getShopCode());
        condition.setCommodityCode(req.getCommodityCode());
        condition.setSpecsId(StringValidater.toLong(req.getSpecsId()));
        condition.setStatus(req.getStatus());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ICommodityOrderDetailAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return commodityOrderDetailAO.queryDetailList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629737Req.class);
        ObjValidater.validateReq(req);
    }

}
