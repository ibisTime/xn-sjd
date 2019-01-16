package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IProductAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629028Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 列表查询认养产品区域
 * @author: silver 
 * @since: Jan 15, 2019 3:19:58 PM 
 * @history:
 */
public class XN629028 extends AProcessor {

    private IProductAO productAO = SpringContextHolder
        .getBean(IProductAO.class);

    private XN629028Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return productAO.queryProductArea();
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629028Req.class);
        ObjValidater.validateReq(req);
    }
}
