/**
 * @Title XN802390.java 
 * @Package com.ogc.standard.api 
 * @Description 
 * @author taojian  
 * @date 2018年9月14日 下午8:48:11 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IAwardMonthAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN802397Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 单个渠道商/用户佣金分布统计
 * @author: taojian 
 * @since: 2018年9月14日 下午8:48:11 
 * @history:
 */
public class XN802397 extends AProcessor {

    private IAwardMonthAO awardAO = SpringContextHolder
        .getBean(IAwardMonthAO.class);

    private XN802397Req req;

    @Override
    public Object doBusiness() throws BizException {
        return awardAO.statistics(req.getUserId());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802397Req.class);
        ObjValidater.validateReq(req);
    }

}
