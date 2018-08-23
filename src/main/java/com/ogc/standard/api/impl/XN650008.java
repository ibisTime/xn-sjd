package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IGroupAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN650008Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询我关注的组合（front）
 * @author: lei 
 * @since: 2018年8月20日 下午9:24:56 
 * @history:
 */
public class XN650008 extends AProcessor {

    private IGroupAO groupAO = SpringContextHolder.getBean(IGroupAO.class);

    private XN650008Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return groupAO.queryMyAttentionGroupPage(start, limit, req.getUserId());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN650008Req.class);
        ObjValidater.validateReq(req);

    }

}
