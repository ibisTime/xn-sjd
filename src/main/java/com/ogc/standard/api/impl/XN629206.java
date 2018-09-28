package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAdoptOrderTreeAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629206Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 详情查询认养权
 * @author: jiafr 
 * @since: 2018年9月28日 下午2:04:42 
 * @history:
 */
public class XN629206 extends AProcessor {

    private IAdoptOrderTreeAO adoptOrderTreeAO = SpringContextHolder
        .getBean(IAdoptOrderTreeAO.class);

    private XN629206Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return adoptOrderTreeAO.getAdoptOrderTree(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629206Req.class);
        ObjValidater.validateReq(req);
    }
}
