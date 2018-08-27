/**
 * @Title XN625100.java 
 * @Package com.cdkj.coin.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月9日 下午7:00:49 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IEthTransactionAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN802530Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 分页查询以太坊广播记录
 * @author: haiqingzheng 
 * @since: 2017年11月9日 下午7:00:49 
 * @history:
 */
public class XN802530 extends AProcessor {

    private IEthTransactionAO ethTransactionAO = SpringContextHolder
        .getBean(IEthTransactionAO.class);

    private XN802530Req req = null;

    /** 
     * @see com.ogc.standard.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        EthTransaction condition = new EthTransaction();
        condition.setHash(req.getHash());
        condition.setBlockHash(req.getBlockHash());
        condition.setBlockNumber(req.getBlockNumber());
        condition.setFrom(req.getFrom());
        condition.setTo(req.getTo());
        condition.setRefNo(req.getRefNo());
        condition.setCreatesStart(req.getDateStart());
        condition.setCreatesEnd(req.getDateEnd());
        condition.setAddress(req.getAddress());
        condition.setOrder("creates", "desc");
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return ethTransactionAO.queryEthTransactionPage(start, limit,
            condition);
    }

    /** 
     * @see com.ogc.standard.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802530Req.class);
        ObjValidater.validateReq(req);
    }

}
