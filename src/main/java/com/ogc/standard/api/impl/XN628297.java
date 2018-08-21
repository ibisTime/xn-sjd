package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IMatchAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.Match;
import com.ogc.standard.dto.req.XN628297Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 列表查赛事
 * @author: silver 
 * @since: 2018年8月21日 下午12:18:53 
 * @history:
 */
public class XN628297 extends AProcessor {
    private IMatchAO matchAO = SpringContextHolder.getBean(IMatchAO.class);

    private XN628297Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Match condition = new Match();
        condition.setStatus(req.getStatus());
        condition.setStartDatetime(DateUtil.strToDate(req.getStartDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));
        condition.setEndDatetime(DateUtil.strToDate(req.getEndDatetime(),
            DateUtil.FRONT_DATE_FORMAT_STRING));

        return matchAO.queryMatchList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN628297Req.class);
        ObjValidater.validateReq(req);
    }
}
