package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ISimuOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN650056Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 【模拟交易】详情查询委托单
 * @author: lei 
 * @since: 2018年8月22日 下午3:32:24 
 * @history:
 */
public class XN650056 extends AProcessor {

    private ISimuOrderAO simuOrderAO = SpringContextHolder
        .getBean(ISimuOrderAO.class);

    private XN650056Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return simuOrderAO.getSimuOrder(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN650056Req.class);
        ObjValidater.validateReq(req);
    }

}
