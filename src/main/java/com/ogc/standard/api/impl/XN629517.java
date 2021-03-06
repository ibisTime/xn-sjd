package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IToolOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.ToolOrder;
import com.ogc.standard.dto.req.XN629517Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 列表查询道具购买订单
 * @author: lei 
 * @since: 2018年10月2日 下午7:49:51 
 * @history:
 */
public class XN629517 extends AProcessor {
    private IToolOrderAO toolOrderAO = SpringContextHolder
        .getBean(IToolOrderAO.class);

    private XN629517Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ToolOrder condition = new ToolOrder();
        condition.setToolCode(req.getToolCode());
        condition.setToolName(req.getToolName());
        condition.setUserId(req.getUserId());
        condition.setStatus(req.getStatus());

        return toolOrderAO.queryToolOrderList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629517Req.class);
        ObjValidater.validateReq(req);
    }
}
