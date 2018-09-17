/**
 * @Title XN625205.java 
 * @Package com.cdkj.coin.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月8日 下午3:16:17 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import org.apache.commons.collections.CollectionUtils;

import com.ogc.standard.ao.IEthSAddressAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.EthSAddress;
import com.ogc.standard.dto.req.XN802605Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 分页查询以太坊补给地址
 * @author: haiqingzheng 
 * @since: 2017年11月8日 下午3:16:17 
 * @history:
 */
public class XN802605 extends AProcessor {

    private IEthSAddressAO ethSAddressAO = SpringContextHolder
        .getBean(IEthSAddressAO.class);

    private XN802605Req req = null;

    /** 
     * @see com.ogc.standard.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        EthSAddress condition = new EthSAddress();
        condition.setAddressForQuery(req.getAddress());
        condition.setStatus(req.getStatus());
        if (CollectionUtils.isNotEmpty(req.getStatusList())) {
            condition.setStatusList(req.getStatusList());
        }
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return ethSAddressAO.queryEthSAddressPage(start, limit, condition);
    }

    /** 
     * @see com.ogc.standard.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802605Req.class);
        ObjValidater.validateReq(req);
    }

}
