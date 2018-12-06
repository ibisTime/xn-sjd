package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.User;
import com.ogc.standard.dto.req.XN805159Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 用户排行榜
 * @author: silver 
 * @since: Dec 6, 2018 3:54:21 PM 
 * @history:
 */
public class XN805159 extends AProcessor {

    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805159Req req;

    @Override
    public Object doBusiness() throws BizException {
        User condition = new User();

        int start = Integer.valueOf(req.getStart());
        int limit = Integer.valueOf(req.getLimit());

        return userAO.queryUserRankPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805159Req.class);
        ObjValidater.validateReq(req);
    }

}
