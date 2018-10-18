package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IBizLogAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.BizLog;
import com.ogc.standard.dto.req.XN629305Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询日志
 * @author: jiafr 
 * @since: 2018年9月28日 下午2:58:11 
 * @history:
 */
public class XN629305 extends AProcessor {

    private IBizLogAO bizLogAO = SpringContextHolder.getBean(IBizLogAO.class);

    private XN629305Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        BizLog condition = new BizLog();
        condition.setAdoptTreeCode(req.getAdoptTreeCode());
        condition.setType(req.getType());
        condition.setUserId(req.getUserId());
        condition.setAdoptUserId(req.getAdoptUserId());
        condition.setQueryUserId(req.getQueryUserId());

        condition.setCreateDatetimeStart(DateUtil.strToDate(
            req.getCreateDatetimeStart(), DateUtil.FRONT_DATE_FORMAT_STRING));
        condition.setCreateDatetimeEnd(DateUtil.strToDate(
            req.getCreateDatetimeEnd(), DateUtil.FRONT_DATE_FORMAT_STRING));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IBizLogAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return bizLogAO.queryBizLogPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629305Req.class);
        ObjValidater.validateReq(req);
    }
}
