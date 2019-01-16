package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ISignLogAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629906Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 养护产权收益
 * @author: silver 
 * @since: Oct 22, 2018 3:52:28 PM 
 * @history:
 */
public class XN629906 extends AProcessor {
    private ISignLogAO signLogAO = SpringContextHolder
        .getBean(ISignLogAO.class);

    private XN629906Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return signLogAO.monthSignStatistics(req.getUserId(),
            DateUtil.strToDate(req.getCreateStartDatetime(),
                DateUtil.DATA_TIME_PATTERN_1),
            DateUtil.strToDate(req.getCreateEndDatetime(),
                DateUtil.DATA_TIME_PATTERN_1));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629906Req.class);
        ObjValidater.validateReq(req);
    }
}
