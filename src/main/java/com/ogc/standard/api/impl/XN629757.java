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
import com.ogc.standard.domain.Comment;
import com.ogc.standard.dto.req.XN629757Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 列表查评论
 * @author: taojian 
 * @since: 2018年11月7日 下午5:08:26 
 * @history:
 */
public class XN629757 extends AProcessor {

    private ICommentAO commentAO = SpringContextHolder
        .getBean(ICommentAO.class);

    private XN629757Req req;

    @Override
    public Object doBusiness() throws BizException {
        Comment condition = new Comment();
        condition.setCommodityCode(req.getCommodityCode());
        condition.setUserId(req.getUserId());
        condition.setParentCode(req.getParentCode());
        condition.setParentUserId(req.getParentUserId());
        condition.setStatus(req.getStatus());
        condition.setStatusList(req.getStatusList());
        return commentAO.queryCommentList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629757Req.class);
        ObjValidater.validateReq(req);
    }

}
