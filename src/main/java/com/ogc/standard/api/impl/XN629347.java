package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IArticleAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.Article;
import com.ogc.standard.dto.req.XN629347Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 列表查询文章
 * @author: jiafr 
 * @since: 2018年10月2日 上午2:51:27 
 * @history:
 */
public class XN629347 extends AProcessor {

    private IArticleAO articleAO = SpringContextHolder
        .getBean(IArticleAO.class);

    private XN629347Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Article condition = new Article();
        condition.setAdoptTreeCode(req.getAdoptTreeCode());
        condition.setTreeNo(req.getTreeNo());
        condition.setType(req.getType());
        condition.setOpenLevel(req.getOpenLevel());
        condition.setStatus(req.getStatus());
        condition.setPublishDatetimeStart(DateUtil.strToDate(
            req.getPublishDatetimeStart(), DateUtil.DATA_TIME_PATTERN_1));
        condition.setPublishDatetimeEnd(DateUtil.strToDate(
            req.getPublishDatetimeEnd(), DateUtil.DATA_TIME_PATTERN_1));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IArticleAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return articleAO.queryArticleList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629347Req.class);
        ObjValidater.validateReq(req);
    }
}
