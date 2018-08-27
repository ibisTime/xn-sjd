/**
 * @Title XN625205.java 
 * @Package com.cdkj.coin.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月8日 下午3:16:17 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN802515Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 分页查询以太坊地址
 * @author: haiqingzheng 
 * @since: 2017年11月8日 下午3:16:17 
 * @history:
 */
public class XN802515 extends AProcessor {

    private IEthAddressAO ethAddressAO = SpringContextHolder
        .getBean(IEthAddressAO.class);

    private XN802515Req req = null;

    /** 
     * @see com.ogc.standard.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        EthAddress condition = new EthAddress();
        condition.setStatusList(req.getStatusList());
        condition.setType(req.getType());
        condition.setAddressForQuery(req.getAddress());
        condition.setUserId(req.getUserId());
        condition.setAccountNumber(req.getAccountNumber());
        condition.setStatus(req.getStatus());
        if (StringUtils.isNotBlank(req.getBalanceStart())) {
            condition.setBalanceStart(
                StringValidater.toBigDecimal(req.getBalanceStart()));
        }
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return ethAddressAO.queryEthAddressPage(start, limit, condition);
    }

    /** 
     * @see com.ogc.standard.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802515Req.class);
        ObjValidater.validateReq(req);
    }

}
