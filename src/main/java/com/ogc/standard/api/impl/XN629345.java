package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IArticleAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Article;
import com.ogc.standard.dto.req.XN629345Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询文章
 * @author: jiafr 
 * @since: 2018年10月2日 上午2:32:38 
 * @history:
 */
public class XN629345 extends AProcessor {

    private IArticleAO articleAO = SpringContextHolder
        .getBean(IArticleAO.class);

    private XN629345Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Article condition = new Article();
        condition.setAdoptTreeCode(req.getAdoptTreeCode());
        condition.setTreeNo(req.getTreeNo());
        condition.setType(req.getType());
        condition.setOpenLevel(req.getOpenLevel());
        condition.setStatus(req.getStatus());
        condition.setIsTop(req.getIsTop());
        condition.setPublishUserId(req.getPublishUserId());

        condition.setPublishDatetimeStart(DateUtil.strToDate(
            req.getPublishDatetimeStart(), DateUtil.FRONT_DATE_FORMAT_STRING));
        condition.setPublishDatetimeEnd(
            DateUtil.getEndDatetime(req.getPublishDatetimeEnd()));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IArticleAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return articleAO.queryArticlePage(start, limit, condition, req);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629345Req.class);
        ObjValidater.validateReq(req);
    }
}
