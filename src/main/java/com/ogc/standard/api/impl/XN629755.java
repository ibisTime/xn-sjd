/**
 * @Title XN629750.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月7日 下午5:08:26 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ICommentAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Comment;
import com.ogc.standard.dto.req.XN629755Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 分页查评论
 * @author: taojian 
 * @since: 2018年11月7日 下午5:08:26 
 * @history:
 */
public class XN629755 extends AProcessor {

    private ICommentAO commentAO = SpringContextHolder
        .getBean(ICommentAO.class);

    private XN629755Req req;

    @Override
    public Object doBusiness() throws BizException {
        Comment condition = new Comment();
        condition.setCommodityCode(req.getCommodityCode());
        condition.setUserId(req.getUserId());
        condition.setParentCode(req.getParentCode());
        condition.setParentUserId(req.getParentUserId());
        condition.setStatus(req.getStatus());
        condition.setStatusList(req.getStatusList());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return commentAO.queryCommentPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629755Req.class);
        ObjValidater.validateReq(req);
    }

}
