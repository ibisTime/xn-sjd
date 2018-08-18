package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ISYSMenuAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN630016Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 菜单-详情
 * @author: xieyj 
 * @since: 2016年5月16日 下午9:39:48 
 * @history:
 */
public class XN630016 extends AProcessor {
    private ISYSMenuAO sysMenuAO = SpringContextHolder
        .getBean(ISYSMenuAO.class);

    private XN630016Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return sysMenuAO.getSYSMenu(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630016Req.class);
        ObjValidater.validateReq(req);
    }
}
