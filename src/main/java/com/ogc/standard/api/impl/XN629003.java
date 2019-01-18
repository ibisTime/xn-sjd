package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICategoryAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629003Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 修改产品分类
 * @author: silver 
 * @since: 2018年9月26日 下午5:38:46 
 * @history:
 */
public class XN629003 extends AProcessor {
    private ICategoryAO categoryAO = SpringContextHolder
        .getBean(ICategoryAO.class);

    private XN629003Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        categoryAO.putOffCategory(req.getCode(), req.getCodeList(),
            req.getUpdater());

        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629003Req.class);
        ObjValidater.validateReq(req);
    }
}
