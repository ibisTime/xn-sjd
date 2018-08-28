package com.ogc.standard.api.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IChargeAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Charge;
import com.ogc.standard.dto.req.XN802345Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 充值订单分页查询
 * @author: xieyj 
 * @since: 2017年5月13日 下午7:27:55 
 * @history:
 */
public class XN802345 extends AProcessor {
    private IChargeAO chargeAO = SpringContextHolder.getBean(IChargeAO.class);

    private XN802345Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Charge condition = new Charge();

        condition.setCodeForQuery(req.getCode());
        condition.setAccountNumber(req.getAccountNumber());
        condition.setAccountType(req.getAccountType());
        condition.setCurrency(req.getCurrency());
        if (CollectionUtils.isNotEmpty(req.getCurrencyList())) {
            condition.setCurrencyList(req.getCurrencyList());
        }
        condition.setBizType(req.getBizType());
        condition.setPayCardNo(req.getPayCardNo());

        condition.setStatus(req.getStatus());
        condition.setApplyUser(req.getApplyUser());
        condition.setApplyDatetimeStart(
            DateUtil.getFrontDate(req.getApplyDateStart(), false));
        condition.setApplyDatetimeEnd(
            DateUtil.getFrontDate(req.getApplyDateEnd(), true));
        condition.setPayUser(req.getPayUser());

        condition.setPayDatetimeStart(
            DateUtil.getFrontDate(req.getPayDateStart(), false));
        condition.setPayDatetimeEnd(
            DateUtil.getFrontDate(req.getPayDateEnd(), true));
        condition.setChannelType(req.getChannelType());
        condition.setChannelOrder(req.getChannelOrder());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IChargeAO.DEFAULT_ORDER_COLUMN;
        }

        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return chargeAO.queryChargePage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802345Req.class);
        ObjValidater.validateReq(req);
    }
}
