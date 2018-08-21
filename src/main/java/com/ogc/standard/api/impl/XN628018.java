package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.ITeamAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Team;
import com.ogc.standard.dto.req.XN628018Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询战队(front)
 * @author: silver 
 * @since: 2018年8月21日 下午5:20:45 
 * @history:
 */
public class XN628018 extends AProcessor {

    private ITeamAO teamAO = SpringContextHolder.getBean(ITeamAO.class);

    private XN628018Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Team condition = new Team();
        condition.setMatchCode(req.getMatchCode());
        condition.setLocation(req.getLocation());
        condition.setStatus(req.getStart());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ITeamAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return teamAO.queryTeamPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN628018Req.class);
        ObjValidater.validateReq(req);
    }
}
