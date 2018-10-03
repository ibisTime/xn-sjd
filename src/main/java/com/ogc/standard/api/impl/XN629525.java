package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IToolUseRecordAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.ToolUseRecord;
import com.ogc.standard.dto.req.XN629525Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询道具使用记录
 * @author: lei 
 * @since: 2018年10月2日 下午7:49:51 
 * @history:
 */
public class XN629525 extends AProcessor {
    private IToolUseRecordAO toolUseRecordAO = SpringContextHolder
        .getBean(IToolUseRecordAO.class);

    private XN629525Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ToolUseRecord condition = new ToolUseRecord();
        condition.setToolOrderCode(req.getToolOrderCode());
        condition.setAdoptTreeCode(req.getAdoptTreeCode());
        condition.setStatus(req.getStatus());
        condition.setUserId(req.getUserId());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IToolUseRecordAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return toolUseRecordAO.queryToolUseRecordPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629525Req.class);
        ObjValidater.validateReq(req);
    }
}
