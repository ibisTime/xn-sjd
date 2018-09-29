package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IMaintainerAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Maintainer;
import com.ogc.standard.dto.req.XN629615Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询养护人
 * @author: silver 
 * @since: 2018年9月29日 上午10:49:25 
 * @history:
 */
public class XN629615 extends AProcessor {
    private IMaintainerAO maintainerAO = SpringContextHolder
        .getBean(IMaintainerAO.class);

    private XN629615Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Maintainer condition = new Maintainer();
        condition.setName(req.getName());
        condition.setMaintainId(req.getMaintainId());
        condition.setMobile(req.getMobile());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IMaintainerAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return maintainerAO.queryMaintainerPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629615Req.class);
        ObjValidater.validateReq(req);
    }
}
