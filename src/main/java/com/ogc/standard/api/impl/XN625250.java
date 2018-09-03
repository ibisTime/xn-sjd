/**
 * @Title XN625240.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月14日 下午12:40:28 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import org.apache.commons.collections.CollectionUtils;

import com.ogc.standard.ao.ITradeOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.TradeOrder;
import com.ogc.standard.dto.req.XN625250Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 分页查询交易订单
 * @author: haiqingzheng 
 * @since: 2017年11月14日 下午12:40:28 
 * @history:
 */
public class XN625250 extends AProcessor {

    private ITradeOrderAO tradeOrderAO = SpringContextHolder
        .getBean(ITradeOrderAO.class);

    private XN625250Req req;

    /** 
     * @see com.ogc.standard.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        TradeOrder condition = new TradeOrder();
        condition.setCodeForQuery(req.getCode());
        condition.setType(req.getType());
        condition.setAdsCode(req.getAdsCode());
        condition.setBuyUser(req.getBuyUser());
        condition.setSellUser(req.getSellUser());
        condition.setTradeCurrency(req.getTradeCurrency());
        condition.setTradeCoin(req.getTradeCoin());
        condition.setPayType(req.getPayType());
        if (CollectionUtils.isNotEmpty(req.getStatusList())) {
            condition.setStatusList(req.getStatusList());
        }
        if (CollectionUtils.isNotEmpty(req.getCurrencyList())) {
            condition.setTradeCoinList(req.getCurrencyList());
        }
        condition.setStatus(req.getStatus());
        condition.setBelongUser(req.getBelongUser());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return tradeOrderAO.queryTradeOrderPage(start, limit, condition);
    }

    /** 
     * @see com.ogc.standard.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN625250Req.class);
        ObjValidater.validateReq(req);
    }

}
