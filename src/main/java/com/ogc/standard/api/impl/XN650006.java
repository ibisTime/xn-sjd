package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IGroupAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN650006Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 详情查询组合（oss）
 * @author: lei 
 * @since: 2018年8月20日 下午9:12:23 
 * @history:
 */
public class XN650006 extends AProcessor {

    private IGroupAO groupAO = SpringContextHolder.getBean(IGroupAO.class);

    private XN650006Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        return groupAO.getGroup(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN650006Req.class);
        ObjValidater.validateReq(req);

    }

}
