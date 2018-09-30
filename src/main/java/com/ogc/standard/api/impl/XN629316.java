package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IVisitorAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629316Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 详情查询来访人
 * @author: jiafr 
 * @since: 2018年9月30日 上午10:32:16 
 * @history:
 */
public class XN629316 extends AProcessor {

    private IVisitorAO visitorAO = SpringContextHolder
        .getBean(IVisitorAO.class);

    private XN629316Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return visitorAO.getVisitor(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629316Req.class);
        ObjValidater.validateReq(req);
    }
}
