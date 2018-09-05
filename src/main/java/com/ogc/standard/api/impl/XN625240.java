/**
 * @Title XN625240.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月14日 下午12:40:28 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import java.math.BigDecimal;

import com.ogc.standard.ao.IAdsAO;
import com.ogc.standard.ao.ITradeOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.TradeOrder;
import com.ogc.standard.dto.req.XN625240Req;
import com.ogc.standard.dto.res.PKCodeRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 我要购买
 * @author: taojian 
 * @since: 2018年9月5日 上午10:15:41 
 * @history:
 */
public class XN625240 extends AProcessor {

    private ITradeOrderAO tradeOrderAO = SpringContextHolder
        .getBean(ITradeOrderAO.class);

    private IAdsAO adsAO = SpringContextHolder.getBean(IAdsAO.class);

    private XN625240Req req;

    @Override
    public Object doBusiness() throws BizException {
        synchronized (XN625240.class) {
            BigDecimal tradePrice = StringValidater
                .toBigDecimal(req.getTradePrice());
            BigDecimal count = StringValidater.toBigDecimal(req.getCount());
            BigDecimal tradeAmount = StringValidater
                .toBigDecimal(req.getTradeAmount());
            TradeOrder tradeOrder = tradeOrderAO.buy(req.getAdsCode(),
                req.getBuyUser(), tradePrice, count, tradeAmount);
            // 检查 是否可以下架
            adsAO.checkXiajia(tradeOrder.getAdsCode());
            return new PKCodeRes(tradeOrder.getCode());
        }
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN625240Req.class);
        req.setBuyUser(operator);
        ObjValidater.validateReq(req);
    }

}
