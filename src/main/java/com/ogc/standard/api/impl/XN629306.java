package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IBizLogAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629306Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 详情查询日志
 * @author: jiafr 
 * @since: 2018年9月28日 下午3:06:54 
 * @history:
 */
public class XN629306 extends AProcessor {

    private IBizLogAO bizLogAO = SpringContextHolder.getBean(IBizLogAO.class);

    private XN629306Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return bizLogAO.getBizLog(req.getId());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629306Req.class);
        ObjValidater.validateReq(req);
    }
}
