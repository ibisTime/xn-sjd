/**
 * @Title XN802575.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年9月11日 下午3:36:11 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IBtcMAddressAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.BtcMAddress;
import com.ogc.standard.dto.req.XN802575Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 分页查散取地址
 * @author: taojian 
 * @since: 2018年9月11日 下午3:36:11 
 * @history:
 */
public class XN802575 extends AProcessor {

    private IBtcMAddressAO btcMAddressAO = SpringContextHolder
        .getBean(IBtcMAddressAO.class);

    private XN802575Req req;

    @Override
    public Object doBusiness() throws BizException {
        BtcMAddress condition = new BtcMAddress();
        condition.setAddressForQuery(req.getAddress());
        condition.setStatusList(req.getStatusList());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return btcMAddressAO.queryBtcMAddressPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802575Req.class);
        ObjValidater.validateReq(req);
    }

}
