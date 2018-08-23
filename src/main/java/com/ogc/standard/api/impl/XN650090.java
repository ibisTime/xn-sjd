package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IDayBenefitAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN650090Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 根据组合编号查询收益率走势（front）
 * @author: lei 
 * @since: 2018年8月21日 上午11:23:26 
 * @history:
 */
public class XN650090 extends AProcessor {

    private IDayBenefitAO dayBenefitAO = SpringContextHolder
        .getBean(IDayBenefitAO.class);

    private XN650090Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return dayBenefitAO.queryDayBenefitList(req.getCode(), req.getDays());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN650090Req.class);
        ObjValidater.validateReq(req);

    }

}
