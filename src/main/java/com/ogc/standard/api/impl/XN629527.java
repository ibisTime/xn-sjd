package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IToolUseRecordAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.ToolUseRecord;
import com.ogc.standard.dto.req.XN629527Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 列表查询道具使用记录
 * @author: lei 
 * @since: 2018年10月2日 下午7:49:51 
 * @history:
 */
public class XN629527 extends AProcessor {
    private IToolUseRecordAO toolUseRecordAO = SpringContextHolder
        .getBean(IToolUseRecordAO.class);

    private XN629527Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ToolUseRecord condition = new ToolUseRecord();
        condition.setToolOrderCode(req.getToolOrderCode());
        condition.setAdoptTreeCode(req.getAdoptTreeCode());
        condition.setStatus(req.getStatus());
        condition.setUserId(req.getUserId());

        return toolUseRecordAO.queryToolUseRecordList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629527Req.class);
        ObjValidater.validateReq(req);
    }
}
