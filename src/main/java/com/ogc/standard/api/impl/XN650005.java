package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IGroupAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Group;
import com.ogc.standard.dto.req.XN650005Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询组合（oss）
 * @author: lei 
 * @since: 2018年8月20日 下午8:32:49 
 * @history:
 */
public class XN650005 extends AProcessor {

    private IGroupAO groupAO = SpringContextHolder.getBean(IGroupAO.class);

    private XN650005Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        Group condition = new Group();
        condition.setMatchCode(req.getMatchCode());
        condition.setTeamCode(req.getTeamCode());
        condition.setUserId(req.getUserId());
        condition.setStatus(req.getStatus());

        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IGroupAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return groupAO.queryGroupPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN650005Req.class);
        ObjValidater.validateReq(req);

    }

}
