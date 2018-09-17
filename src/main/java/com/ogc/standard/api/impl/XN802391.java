/**
 * @Title XN802391.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年9月17日 下午9:06:28 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAwardAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN802391Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 申请特殊奖励
 * @author: taojian 
 * @since: 2018年9月17日 下午9:06:28 
 * @history:
 */
public class XN802391 extends AProcessor {
    private IAwardAO awardAO = SpringContextHolder.getBean(IAwardAO.class);

    private XN802391Req req;

    @Override
    public Object doBusiness() throws BizException {
        awardAO.addSpecialAward(req.getUserId(),
            StringValidater.toBigDecimal(req.getCount()), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802391Req.class);
        ObjValidater.validateReq(req);
    }

}
