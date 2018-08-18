package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ISYSConfigAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.SYSConfig;
import com.ogc.standard.dto.req.XN630042Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 修改系统参数
 * @author: xieyj 
 * @since: 2016年9月17日 下午1:54:21 
 * @history:
 */
public class XN630042 extends AProcessor {
    private ISYSConfigAO sysConfigAO = SpringContextHolder
        .getBean(ISYSConfigAO.class);

    private XN630042Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSConfig data = new SYSConfig();
        data.setId(StringValidater.toLong(req.getId()));
        data.setCvalue(req.getCvalue());
        data.setUpdater(req.getUpdater());
        data.setRemark(req.getRemark());
        sysConfigAO.editSYSConfig(data);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630042Req.class);
        ObjValidater.validateReq(req);

    }

}
