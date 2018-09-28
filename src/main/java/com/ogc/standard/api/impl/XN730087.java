package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IAgentUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.AgentUser;
import com.ogc.standard.dto.req.XN730087Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 列表查询代理用户
 * @author: silver 
 * @since: 2018年9月28日 下午4:43:17 
 * @history:
 */
public class XN730087 extends AProcessor {
    private IAgentUserAO agentUserAO = SpringContextHolder
        .getBean(IAgentUserAO.class);

    private XN730087Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        AgentUser condition = new AgentUser();
        condition.setStatus(req.getStatus());
        condition.setType(req.getType());
        condition.setLevel(req.getLevel());
        condition.setKeyword(req.getKeyword());
        condition.setDateStart(DateUtil.strToDate(req.getDateStart(),
            DateUtil.DATA_TIME_PATTERN_1));
        condition.setDateEnd(
            DateUtil.strToDate(req.getDateEnd(), DateUtil.DATA_TIME_PATTERN_1));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IAgentUserAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return agentUserAO.queryAgentUserList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN730087Req.class);
        ObjValidater.validateReq(req);
    }
}
