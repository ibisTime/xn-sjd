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
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Award;
import com.ogc.standard.dto.req.XN802395Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 申请特殊奖励
 * @author: taojian 
 * @since: 2018年9月17日 下午9:06:28 
 * @history:
 */
public class XN802395 extends AProcessor {
    private IAwardAO awardAO = SpringContextHolder.getBean(IAwardAO.class);

    private XN802395Req req;

    @Override
    public Object doBusiness() throws BizException {
        Award condition = new Award();
        condition.setUserId(req.getUserId());
        condition.setUserKind(req.getUserKind());
        condition.setCreateDatetimeStart(
            DateUtil.getFrontDate(req.getDateStart(), false));
        condition.setCreateDatetimeEnd(
            DateUtil.getFrontDate(req.getDateEnd(), true));
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return awardAO.queryAwardPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802395Req.class);
        ObjValidater.validateReq(req);
    }

}
