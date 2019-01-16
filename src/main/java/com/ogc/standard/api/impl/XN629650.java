package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ISearchHistoryAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629650Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 添加搜索历史
 * @author: silver 
 * @since: Jan 15, 2019 4:09:30 PM 
 * @history:
 */
public class XN629650 extends AProcessor {
    private ISearchHistoryAO searchHistoryAO = SpringContextHolder
        .getBean(ISearchHistoryAO.class);

    private XN629650Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        searchHistoryAO.addSearchHistory(req.getUserId(), req.getType(),
            req.getContent());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629650Req.class);
        ObjValidater.validateReq(req);
    }
}
