package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IDeriveGroupAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.DeriveGroup;
import com.ogc.standard.dto.req.XN629459Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 列表查询品种
 * @author: silver 
 * @since: Nov 4, 2018 3:28:16 PM 
 * @history:
 */
public class XN629459 extends AProcessor {
    private IDeriveGroupAO deriveGroupAO = SpringContextHolder
        .getBean(IDeriveGroupAO.class);

    private XN629459Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        DeriveGroup condition = new DeriveGroup();
        condition.setType(req.getType());
        condition.setMinQuantity(req.getMinQuantity());
        condition.setStatus(req.getStatus());

        return deriveGroupAO.queryVarietyList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629459Req.class);
        ObjValidater.validateReq(req);
    }

}
