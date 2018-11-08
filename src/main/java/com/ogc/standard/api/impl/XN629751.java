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
import com.ogc.standard.dto.req.XN629751Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 审核
 * @author: taojian 
 * @since: 2018年11月7日 下午5:08:26 
 * @history:
 */
public class XN629751 extends AProcessor {

    private ICommentAO commentAO = SpringContextHolder
        .getBean(ICommentAO.class);

    private XN629751Req req;

    @Override
    public Object doBusiness() throws BizException {
        commentAO.approveComment(req.getCode(), req.getApproveResult(),
            req.getApprover(), req.getApproveNote());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629751Req.class);
        ObjValidater.validateReq(req);
    }

}
