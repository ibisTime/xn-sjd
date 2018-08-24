package com.ogc.standard.api.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IPostAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Post;
import com.ogc.standard.dto.req.XN628058Req;
import com.ogc.standard.enums.EPostStatus;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询帖子(front)
 * @author: silver 
 * @since: 2018年8月22日 下午4:17:49 
 * @history:
 */
public class XN628058 extends AProcessor {

    private IPostAO postAO = SpringContextHolder.getBean(IPostAO.class);

    private XN628058Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Post condition = new Post();
        condition.setUserId(req.getUserId());

        List<String> statusList = new ArrayList<String>();
        statusList.add(EPostStatus.TO_APPROVE.getCode());
        statusList.add(EPostStatus.APPROVED_YES.getCode());
        statusList.add(EPostStatus.APPROVED_NO.getCode());
        statusList.add(EPostStatus.RELEASED.getCode());
        condition.setStatusList(statusList);

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IPostAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return postAO.queryPostPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN628058Req.class);
        ObjValidater.validateReq(req);
    }
}
