package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ISimuOrderAO;
/**
 * @Title XN628500.java 
 * @Package com.cdkj.info.api.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2018年4月18日 下午4:15:24 
 * @version V1.0   
 */
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN650050Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 【模拟交易】委托下单
 * @author: lei 
 * @since: 2018年8月22日 下午2:04:15 
 * @history:
 */
public class XN650050 extends AProcessor {

    private ISimuOrderAO simuOrderAO = SpringContextHolder
        .getBean(ISimuOrderAO.class);

    private XN650050Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        String code = simuOrderAO.submit(req);
        simuOrderAO.buySuccessOrder(code);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN650050Req.class);
        ObjValidater.validateReq(req);
    }
}
