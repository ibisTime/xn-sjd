package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICategoryAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN629000Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 新增产品分类
 * @author: silver 
 * @since: 2018年9月26日 下午5:34:31 
 * @history:
 */
public class XN629000 extends AProcessor {
    private ICategoryAO categoryAO = SpringContextHolder
        .getBean(ICategoryAO.class);

    private XN629000Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return categoryAO.addCategory(req.getName(), req.getParentCode(),
            req.getPic(), StringValidater.toInteger(req.getOrderNo()),
            req.getUpdater(), req.getRemark());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629000Req.class);
        ObjValidater.validateReq(req);
    }
}
