package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IMaintainRecordAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629630Req;
import com.ogc.standard.dto.res.PKCodeRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 添加养护记录
 * @author: silver 
 * @since: 2018年9月29日 上午10:49:25 
 * @history:
 */
public class XN629630 extends AProcessor {
    private IMaintainRecordAO maintainRecordAO = SpringContextHolder
        .getBean(IMaintainRecordAO.class);

    private XN629630Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(maintainRecordAO.addMaintainRecord(req));
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629630Req.class);
        ObjValidater.validateReq(req);
    }
}
