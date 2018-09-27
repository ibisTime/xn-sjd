package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IProductAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629026Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 详情查询认养产品
 * @author: silver 
 * @since: 2018年9月27日 上午10:52:14 
 * @history:
 */
public class XN629026 extends AProcessor {

    private IProductAO productAO = SpringContextHolder
        .getBean(IProductAO.class);

    private XN629026Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return productAO.getProduct(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629026Req.class);
        ObjValidater.validateReq(req);
    }
}
