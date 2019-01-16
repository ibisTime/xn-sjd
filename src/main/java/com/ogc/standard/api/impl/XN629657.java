package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.ISearchHistoryAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.SearchHistory;
import com.ogc.standard.dto.req.XN629657Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 列表查询搜索历史
 * @author: silver 
 * @since: Jan 15, 2019 4:27:00 PM 
 * @history:
 */
public class XN629657 extends AProcessor {

    private ISearchHistoryAO searchHistoryAO = SpringContextHolder
        .getBean(ISearchHistoryAO.class);

    private XN629657Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SearchHistory condition = new SearchHistory();
        condition.setType(req.getType());
        condition.setUserId(req.getUserId());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ISearchHistoryAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return searchHistoryAO.querySearchHistoryList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629657Req.class);
        ObjValidater.validateReq(req);
    }
}
