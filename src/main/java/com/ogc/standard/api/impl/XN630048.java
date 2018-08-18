package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ISYSConfigAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN630048Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 根据类型查询系统参数
 * @author: xieyj 
 * @since: 2016年9月17日 下午1:55:07 
 * @history:
 */
public class XN630048 extends AProcessor {
    private ISYSConfigAO sysConfigAO = SpringContextHolder
        .getBean(ISYSConfigAO.class);

    private XN630048Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return sysConfigAO.getConfigsMap(req.getType());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630048Req.class);
        ObjValidater.validateReq(req);
    }

}
