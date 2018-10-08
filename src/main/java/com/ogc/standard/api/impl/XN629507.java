package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IToolAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.Tool;
import com.ogc.standard.dto.req.XN629507Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 列表查询道具
 * @author: lei 
 * @since: 2018年10月2日 下午7:49:51 
 * @history:
 */
public class XN629507 extends AProcessor {
    private IToolAO toolAO = SpringContextHolder.getBean(IToolAO.class);

    private XN629507Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Tool condition = new Tool();
        condition.setNameForQuery(req.getName());
        condition.setType(req.getType());
        condition.setStatus(req.getStatus());
        condition.setUserId(req.getUserId());
        condition.setUpdater(req.getUpdater());
        return toolAO.queryToolList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629507Req.class);
        ObjValidater.validateReq(req);
    }
}
