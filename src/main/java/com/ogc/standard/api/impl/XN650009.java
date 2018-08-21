package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IGroupAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN650009Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 详情查询组合，包含币种配置和最新成交记录（front）
 * @author: lei 
 * @since: 2018年8月20日 下午9:34:20 
 * @history:
 */
public class XN650009 extends AProcessor {

    private IGroupAO groupAO = SpringContextHolder.getBean(IGroupAO.class);

    private XN650009Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        return groupAO.getGroupByVisitUserId(req.getCode(),
            req.getVisitUserId());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN650009Req.class);
        ObjValidater.validateReq(req);

    }

}
