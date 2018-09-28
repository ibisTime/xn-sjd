package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IGiveTreeRecordAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629336Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 详情查询赠送记录
 * @author: jiafr 
 * @since: 2018年9月28日 下午2:30:00 
 * @history:
 */
public class XN629336 extends AProcessor {

    private IGiveTreeRecordAO giveTreeRecordAO = SpringContextHolder
        .getBean(IGiveTreeRecordAO.class);

    private XN629336Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return giveTreeRecordAO.getGiveTreeRecord(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629336Req.class);
        ObjValidater.validateReq(req);
    }
}
