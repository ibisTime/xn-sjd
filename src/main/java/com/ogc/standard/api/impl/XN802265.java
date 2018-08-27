/**
 * @Title XN802250.java 
 * @Package com.cdkj.coin.wallet.api.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2018年3月13日 上午11:12:25 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.cdkj.coin.wallet.ao.ICoinAO;
import com.cdkj.coin.wallet.api.AProcessor;
import com.cdkj.coin.wallet.common.JsonUtil;
import com.cdkj.coin.wallet.core.ObjValidater;
import com.cdkj.coin.wallet.core.StringValidater;
import com.cdkj.coin.wallet.domain.Coin;
import com.cdkj.coin.wallet.dto.req.XN802265Req;
import com.cdkj.coin.wallet.exception.BizException;
import com.cdkj.coin.wallet.exception.ParaException;
import com.cdkj.coin.wallet.spring.SpringContextHolder;

/** 
 * 分页查
 * @author: haiqingzheng 
 * @since: 2018年3月13日 上午11:12:25 
 * @history:
 */
public class XN802265 extends AProcessor {

    private ICoinAO coinAO = SpringContextHolder.getBean(ICoinAO.class);

    private XN802265Req req = null;

    /** 
     * @see com.cdkj.coin.wallet.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        Coin condition = new Coin();
        condition.setSymbolForQuery(req.getSymbol());
        condition.setEname(req.getEname());
        condition.setCname(req.getCname());
        condition.setType(req.getType());
        condition.setContractAddress(req.getContractAddress());
        condition.setStatus(req.getStatus());
        condition.setOrder(ICoinAO.DEFAULT_ORDER_COLUMN, "asc");
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return coinAO.queryCoinPage(start, limit, condition);
    }

    /** 
     * @see com.cdkj.coin.wallet.api.IProcessor#doCheck(java.lang.String, java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802265Req.class);
        ObjValidater.validateReq(req);
    }

}
