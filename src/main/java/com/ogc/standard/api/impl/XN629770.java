/**
 * @Title XN629770.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月8日 上午10:46:44 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAfterSaleAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN629770Req;
import com.ogc.standard.dto.res.PKCodeRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 申请售后（退货退款）
 * @author: taojian 
 * @since: 2018年11月8日 上午10:46:44 
 * @history:
 */
public class XN629770 extends AProcessor {

    private IAfterSaleAO afterSaleAO = SpringContextHolder
        .getBean(IAfterSaleAO.class);

    private XN629770Req req;

    @Override
    public Object doBusiness() throws BizException {

        return new PKCodeRes(afterSaleAO.applyGoods(req.getOrderDetailCode(),
            req.getLogisticsCompany(), req.getLogisticsNumber(),
            StringValidater.toBigDecimal(req.getRefundAmount()),
            req.getDeliver()));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629770Req.class);
        ObjValidater.validateReq(req);
    }

}
