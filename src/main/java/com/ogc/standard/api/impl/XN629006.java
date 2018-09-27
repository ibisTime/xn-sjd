package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICategoryAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629006Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 详细查产品分类
 * @author: silver 
 * @since: 2018年9月26日 下午5:45:52 
 * @history:
 */
public class XN629006 extends AProcessor {

    private ICategoryAO categoryAO = SpringContextHolder
        .getBean(ICategoryAO.class);

    private XN629006Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return categoryAO.getCategory(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629006Req.class);
        ObjValidater.validateReq(req);
    }
}
