package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IBarrageAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629386Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询日志
 * @author: jiafr 
 * @since: 2018年9月28日 下午2:58:11 
 * @history:
 */
public class XN629386 extends AProcessor {

    private IBarrageAO barrageAO = SpringContextHolder
        .getBean(IBarrageAO.class);

    private XN629386Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return barrageAO.getBarrage(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629386Req.class);
        ObjValidater.validateReq(req);
    }
}
