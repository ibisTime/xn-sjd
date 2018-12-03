package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.ICNavigateAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.CNavigate;
import com.ogc.standard.dto.req.XN630505Req;
import com.ogc.standard.enums.EBoolean;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 分页查询导航
 * @author: zuixian 
 * @since: 2016年10月10日 下午3:58:13 
 * @history:
 */
public class XN630505 extends AProcessor {
    private ICNavigateAO cNavigateAO = SpringContextHolder
        .getBean(ICNavigateAO.class);

    private XN630505Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        CNavigate condition = new CNavigate();
        condition.setShopCode(req.getShopCode());
        condition.setName(req.getName());
        condition.setType(req.getType());
        condition.setStatus(req.getStatus());
        condition.setLocation(req.getLocation());
        condition.setParentCode(req.getParentCode());
        condition.setIsFront(EBoolean.NO.getCode());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ICNavigateAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return cNavigateAO.queryCNavigatePage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630505Req.class);
        ObjValidater.validateReq(req);
    }
}
