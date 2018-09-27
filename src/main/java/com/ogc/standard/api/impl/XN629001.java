package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICategoryAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN629001Req;
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
public class XN629001 extends AProcessor {
    private ICategoryAO categoryAO = SpringContextHolder
        .getBean(ICategoryAO.class);

    private XN629001Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        categoryAO.editCategory(req.getCode(), req.getName(),
            req.getParentCode(), req.getPic(),
            StringValidater.toInteger(req.getOrderNo()), req.getUpdater(),
            req.getRemark());

        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629001Req.class);
        ObjValidater.validateReq(req);
    }
}
