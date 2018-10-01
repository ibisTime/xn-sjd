package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IGiftOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629326Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 详情查询礼物订单
 * @author: jiafr 
 * @since: 2018年9月30日 下午2:11:27 
 * @history:
 */
public class XN629326 extends AProcessor {

    private IGiftOrderAO giftOrderAO = SpringContextHolder
        .getBean(IGiftOrderAO.class);

    private XN629326Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return giftOrderAO.getGiftOrder(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629326Req.class);
        ObjValidater.validateReq(req);
    }
}
