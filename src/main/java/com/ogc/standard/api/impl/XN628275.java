package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.ICommentAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Comment;
import com.ogc.standard.dto.req.XN628275Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询评论(oss)
 * @author: silver 
 * @since: 2018年8月21日 下午12:11:35 
 * @history:
 */
public class XN628275 extends AProcessor {

    private ICommentAO commentAO = SpringContextHolder
        .getBean(ICommentAO.class);

    private XN628275Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Comment condition = new Comment();
        condition.setStatus(req.getStatus());
        condition.setType(req.getType());
        condition.setContent(req.getContent());
        condition.setCommentDateStart(DateUtil.strToDate(
            req.getCommentDateStart(), DateUtil.FRONT_DATE_FORMAT_STRING));
        condition.setCommentDateEnd(DateUtil.strToDate(req.getCommentDateEnd(),
            DateUtil.FRONT_DATE_FORMAT_STRING));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ICommentAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return commentAO.queryCommentPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN628275Req.class);
        ObjValidater.validateReq(req);
    }
}
