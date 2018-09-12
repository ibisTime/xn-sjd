package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ISimuKLineAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN650066Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 行情K线查询
 * @author: lei 
 * @since: 2018年8月29日 下午3:51:38 
 * @history:
 */
public class XN650066 extends AProcessor {

    private ISimuKLineAO simuKLineAO = SpringContextHolder
        .getBean(ISimuKLineAO.class);

    private XN650066Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        return simuKLineAO.querySimuKLineList(req.getSymbol(),
            req.getToSymbol(), req.getPeriod());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN650066Req.class);
        ObjValidater.validateReq(req);
    }

}
