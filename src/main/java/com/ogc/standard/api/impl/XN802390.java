/**
 * @Title XN802390.java 
 * @Package com.ogc.standard.api 
 * @Description 
 * @author taojian  
 * @date 2018年9月14日 下午8:48:11 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAwardAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN802390Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 渠道商佣金结算
 * @author: taojian 
 * @since: 2018年9月14日 下午8:48:11 
 * @history:
 */
public class XN802390 extends AProcessor {

    private IAwardAO awardAO = SpringContextHolder.getBean(IAwardAO.class);

    private XN802390Req req;

    @Override
    public Object doBusiness() throws BizException {
        awardAO.settle(StringValidater.toLong(req.getId()), req.getIsSettle(),
            req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802390Req.class);
        ObjValidater.validateReq(req);
    }

}
