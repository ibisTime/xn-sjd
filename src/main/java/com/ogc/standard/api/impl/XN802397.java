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
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Award;
import com.ogc.standard.dto.req.XN802397Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 用户佣金明细分页查询
 * @author: taojian 
 * @since: 2018年9月14日 下午8:48:11 
 * @history:
 */
public class XN802397 extends AProcessor {

    private IAwardAO awardAO = SpringContextHolder.getBean(IAwardAO.class);

    private XN802397Req req;

    @Override
    public Object doBusiness() throws BizException {
        Award condition = new Award();
        condition.setUserId(req.getUserId());
        condition.setUserKind(req.getUserKind());
        condition.setCreateDatetimeStart(
            DateUtil.getStartDatetime(req.getDateStart()));
        condition
            .setCreateDatetimeEnd(DateUtil.getEndDatetime(req.getDateEnd()));
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return awardAO.queryAwardPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802397Req.class);
        ObjValidater.validateReq(req);
    }

}
