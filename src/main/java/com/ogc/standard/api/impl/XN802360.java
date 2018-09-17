/**
 * @Title XN625100.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月9日 下午7:00:49 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import java.math.BigDecimal;

import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;

import com.ogc.standard.ao.ICollectAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN802360Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.enums.EOriginialCoin;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * ETH手动归集
 * @author: haiqingzheng 
 * @since: 2017年11月9日 下午7:00:49 
 * @history:
 */
public class XN802360 extends AProcessor {
    private ICollectAO collectAO = SpringContextHolder
        .getBean(ICollectAO.class);

    private XN802360Req req = null;

    /** 
     * @see com.ogc.standard.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        BigDecimal balanceStart = Convert.toWei(
            StringValidater.toBigDecimal(req.getBalanceStart()), Unit.ETHER);
        collectAO.collect(balanceStart, EOriginialCoin.ETH.getCode(), null);
        return new BooleanRes(true);
    }

    /** 
     * @see com.ogc.standard.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802360Req.class);
    }

}
