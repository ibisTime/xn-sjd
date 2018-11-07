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
import com.ogc.standard.dto.req.XN629750Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 评论
 * @author: taojian 
 * @since: 2018年11月7日 下午5:08:26 
 * @history:
 */
public class XN629750 extends AProcessor {

    private ICommentAO commentAO = SpringContextHolder
        .getBean(ICommentAO.class);

    private XN629750Req req;

    @Override
    public Object doBusiness() throws BizException {
        return commentAO.comment(req.getUserId(), req.getCommodityCode(),
            req.getContent(), req.getParentCode(), req.getParentUserId());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629750Req.class);
        ObjValidater.validateReq(req);
    }

}
