package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IAdoptOrderTreeAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.AdoptOrderTree;
import com.ogc.standard.dto.req.XN629209Req;
import com.ogc.standard.dto.req.XN629210Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 列表查询认养权树种
 * @author: silver 
 * @since: Jan 24, 2019 2:34:06 PM 
 * @history:
 */
public class XN629210 extends AProcessor {

    private IAdoptOrderTreeAO adoptOrderTreeAO = SpringContextHolder
        .getBean(IAdoptOrderTreeAO.class);

    private XN629210Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return adoptOrderTreeAO.getVariety();
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629210Req.class);
        ObjValidater.validateReq(req);
    }
}
