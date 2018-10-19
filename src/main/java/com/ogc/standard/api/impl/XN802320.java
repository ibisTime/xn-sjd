package com.ogc.standard.api.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IJourAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Jour;
import com.ogc.standard.dto.req.XN802320Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 流水分页查询(oss)
 * @author: xieyj 
 * @since: 2016年12月24日 上午7:59:19 
 * @history:
 */
public class XN802320 extends AProcessor {

    private IJourAO jourAO = SpringContextHolder.getBean(IJourAO.class);

    private XN802320Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        Jour condition = new Jour();

        condition.setType(req.getType());
        condition.setUserId(req.getUserId());
        condition.setAccountNumber(req.getAccountNumber());
        condition.setAccountType(req.getAccountType());
        condition.setCurrency(req.getCurrency());

        condition.setBizType(req.getBizType());
        condition.setStatus(req.getStatus());
        condition.setChannelType(req.getChannelType());
        condition.setChannelOrder(req.getChannelOrder());
        condition.setRefNo(req.getRefNo());

        condition.setRelaNameForQuery(req.getRelaNameForQuery());

        if (CollectionUtils.isNotEmpty(req.getCurrencyList())) {
            condition.setCurrencyList(req.getCurrencyList());
        }

        condition.setCreateDatetimeStart(DateUtil.strToDate(
            req.getCreateDatetimeStart(), DateUtil.FRONT_DATE_FORMAT_STRING));
        condition.setCreateDatetimeEnd(
            DateUtil.getEndDatetime(req.getCreateDatetimeEnd()));

        condition.setWorkDate(req.getWorkDate());
        condition.setCheckUser(req.getCheckUser());
        condition.setAdjustUser(req.getAdjustUser());

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IJourAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return jourAO.queryJourPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802320Req.class);
        ObjValidater.validateReq(req);
    }
}
