package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICNavigateAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.CNavigate;
import com.ogc.standard.dto.req.XN630506Req;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 前端查询导航
 * @author: xieyj 
 * @since: 2016年10月25日 下午4:51:09 
 * @history:
 */
public class XN630506 extends AProcessor {
    private ICNavigateAO cNavigateAO = SpringContextHolder
        .getBean(ICNavigateAO.class);

    private XN630506Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        CNavigate condition = new CNavigate();
        condition.setType(req.getType());
        condition.setParentCode(req.getParentCode());
        condition.setLocation(req.getLocation());
        condition.setStatus(EBoolean.YES.getCode());
        condition.setIsFront(EBoolean.YES.getCode());
        return cNavigateAO.queryCNavigateList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630506Req.class);
        ObjValidater.validateReq(req);
    }
}
