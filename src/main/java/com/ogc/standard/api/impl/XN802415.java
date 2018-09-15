package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IDivideAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Divide;
import com.ogc.standard.dto.req.XN802415Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 分红明细分页查询
 * @author: lei 
 * @since: 2018年9月15日 下午5:12:07 
 * @history:
 */
public class XN802415 extends AProcessor {

    private IDivideAO divideAO = SpringContextHolder.getBean(IDivideAO.class);

    private XN802415Req req;

    @Override
    public Object doBusiness() throws BizException {

        Divide condition = new Divide();
        condition.setStatus(req.getStatus());
        condition.setCreateDatetimeStart(
            DateUtil.getFrontDate(req.getDateStart(), false));
        condition.setCreateDatetimeEnd(
            DateUtil.getFrontDate(req.getDateEnd(), true));

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IDivideAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        
        return divideAO.queryDividePage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802415Req.class);
        ObjValidater.validateReq(req);
    }

}
