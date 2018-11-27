package com.ogc.standard.api.impl;

import java.util.Date;

import com.ogc.standard.ao.ISYSUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629901Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 新增用户统计
 * @author: silver 
 * @since: Oct 22, 2018 11:16:36 AM 
 * @history:
 */
public class XN629901 extends AProcessor {
    private ISYSUserAO sysUserAO = SpringContextHolder
        .getBean(ISYSUserAO.class);

    private XN629901Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Date createDatetimeStart = DateUtil
            .getStartDatetime(req.getCreateDatetimeStart());
        Date createDatetimeEnd = DateUtil
            .getEndDatetime(req.getCreateDatetimeEnd());

        return sysUserAO.getTotalCreateCount(req.getUserId(), req.getType(),
            createDatetimeStart, createDatetimeEnd);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629901Req.class);
        ObjValidater.validateReq(req);
    }
}
