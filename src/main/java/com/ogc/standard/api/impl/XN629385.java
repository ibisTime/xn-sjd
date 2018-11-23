package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IBarrageAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Barrage;
import com.ogc.standard.dto.req.XN629385Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询日志
 * @author: jiafr 
 * @since: 2018年9月28日 下午2:58:11 
 * @history:
 */
public class XN629385 extends AProcessor {

    private IBarrageAO barrageAO = SpringContextHolder
        .getBean(IBarrageAO.class);

    private XN629385Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Barrage condition = new Barrage();
        condition.setCode(req.getCode());
        condition.setStatus(req.getStatus());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IBarrageAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return barrageAO.queryBarragePage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629385Req.class);
        ObjValidater.validateReq(req);
    }
}
