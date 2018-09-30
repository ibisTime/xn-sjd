package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IShareRecordAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.ShareRecord;
import com.ogc.standard.dto.req.XN629375Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询分享
 * @author: silver 
 * @since: Sep 30, 2018 12:33:06 PM 
 * @history:
 */
public class XN629375 extends AProcessor {
    private IShareRecordAO shareRecordAO = SpringContextHolder
        .getBean(IShareRecordAO.class);

    private XN629375Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        ShareRecord condition = new ShareRecord();
        condition.setUserId(req.getUserId());
        condition.setChannel(req.getChannel());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IShareRecordAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return shareRecordAO.queryShareRecordPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629375Req.class);
        ObjValidater.validateReq(req);
    }
}
