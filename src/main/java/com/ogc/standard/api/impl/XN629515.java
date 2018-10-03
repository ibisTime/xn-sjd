package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IToolOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.ToolOrder;
import com.ogc.standard.dto.req.XN629515Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询道具购买订单
 * @author: lei 
 * @since: 2018年10月2日 下午7:49:51 
 * @history:
 */
public class XN629515 extends AProcessor {
    private IToolOrderAO toolOrderAO = SpringContextHolder
        .getBean(IToolOrderAO.class);

    private XN629515Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ToolOrder condition = new ToolOrder();
        condition.setToolCode(req.getToolCode());
        condition.setToolNameForQuery(req.getToolName());
        condition.setUserId(req.getUserId());
        condition.setStatus(req.getStatus());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IToolOrderAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return toolOrderAO.queryToolOrderPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629515Req.class);
        ObjValidater.validateReq(req);
    }
}
