/**
 * @Title XN802590.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年9月11日 下午8:43:12 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IBtcTransactionAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.BtcTransaction;
import com.ogc.standard.dto.req.XN802590Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 分页查询btc交易记录
 * @author: taojian 
 * @since: 2018年9月11日 下午8:43:12 
 * @history:
 */
public class XN802590 extends AProcessor {
    private IBtcTransactionAO btcTransaction = SpringContextHolder
        .getBean(IBtcTransactionAO.class);

    private XN802590Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        BtcTransaction condition = new BtcTransaction();
        condition.setBlockhash(req.getBlockHash());
        condition.setBlockheight(StringValidater.toLong(req.getBlockHeight()));
        condition.setTxid(req.getTxid());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return btcTransaction.queryBtcTransactionPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802590Req.class);
        ObjValidater.validateReq(req);
    }

}
