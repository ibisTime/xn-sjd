package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IBizLogAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.BizLog;
import com.ogc.standard.dto.req.XN629307Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 列表查询日志
 * @author: jiafr 
 * @since: 2018年9月28日 下午3:09:37 
 * @history:
 */
public class XN629307 extends AProcessor {
    private IBizLogAO bizLogAO = SpringContextHolder.getBean(IBizLogAO.class);

    private XN629307Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        BizLog condition = new BizLog();
        condition.setAdoptTreeCode(req.getAdoptTreeCode());
        condition.setType(req.getType());
        condition.setUserId(req.getUserId());
        condition.setQueryUserId(req.getQueryUserId());

        condition.setCreateDatetimeStart(DateUtil.strToDate(
            req.getCreateDatetimeStart(), DateUtil.DATA_TIME_PATTERN_1));
        condition.setCreateDatetimeEnd(DateUtil.strToDate(
            req.getCreateDatetimeEnd(), DateUtil.DATA_TIME_PATTERN_1));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IBizLogAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return bizLogAO.queryBizLogList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629307Req.class);
        ObjValidater.validateReq(req);
    }
}
