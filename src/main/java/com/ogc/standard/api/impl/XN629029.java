package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IProductAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629029Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 列表查询认养产品品种
 * @author: silver 
 * @since: Jan 23, 2019 4:03:32 PM 
 * @history:
 */
public class XN629029 extends AProcessor {

    private IProductAO productAO = SpringContextHolder
        .getBean(IProductAO.class);

    private XN629029Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return productAO.queryProductVariety();
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629029Req.class);
        ObjValidater.validateReq(req);
    }
}
