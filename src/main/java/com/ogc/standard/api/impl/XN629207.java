package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IAdoptOrderTreeAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.dto.req.XN629207Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 列表查询认养权
 * @author: jiafr 
 * @since: 2018年9月28日 下午2:08:07 
 * @history:
 */
public class XN629207 extends AProcessor {
    private IAdoptOrderTreeAO adoptOrderTreeAO = SpringContextHolder
        .getBean(IAdoptOrderTreeAO.class);

    private XN629207Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        AdoptOrderTree condition = new AdoptOrderTree();
        condition.setOrderCode(req.getOrderCode());
        condition.setStatus(req.getStatus());
        condition.setCurrentHolder(req.getCurrentHolder());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IAdoptOrderTreeAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return adoptOrderTreeAO.queryAdoptOrderTreeList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629207Req.class);
        ObjValidater.validateReq(req);
    }
}
