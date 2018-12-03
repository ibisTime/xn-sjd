package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IPresellLogisticsAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629820Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 查询物流轨迹
 * @author: silver 
 * @since: Nov 23, 2018 1:49:46 PM 
 * @history:
 */
public class XN629820 extends AProcessor {

    private IPresellLogisticsAO presellLogisticsAO = SpringContextHolder
        .getBean(IPresellLogisticsAO.class);

    private XN629820Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return presellLogisticsAO.trackQuery(req.getExpCode(), req.getExpNo());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629820Req.class);
        ObjValidater.validateReq(req);
    }
}
