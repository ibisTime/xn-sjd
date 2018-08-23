package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IGroupAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.Group;
import com.ogc.standard.dto.req.XN650007Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 列表查询我的组合（front）
 * @author: lei 
 * @since: 2018年8月20日 下午9:22:00 
 * @history:
 */
public class XN650007 extends AProcessor {

    private IGroupAO groupAO = SpringContextHolder.getBean(IGroupAO.class);

    private XN650007Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        Group condition = new Group();
        condition.setUserId(req.getUserId());
        return groupAO.queryGroupList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN650007Req.class);
        ObjValidater.validateReq(req);

    }

}
