/**
 * @Title XN805145.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author dl  
 * @date 2018年8月20日 下午7:04:50 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.ISignLogAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.SignLog;
import com.ogc.standard.dto.req.XN805145Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 分页查询日志
 * @author: dl 
 * @since: 2018年8月20日 下午7:04:50 
 * @history:
 */
public class XN805145 extends AProcessor {
    private ISignLogAO signLogAO = SpringContextHolder
        .getBean(ISignLogAO.class);

    private XN805145Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SignLog condition = new SignLog();
        condition.setUserId(req.getUserId());
        condition.setType(req.getType());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ISignLogAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return signLogAO.querySignLogPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805145Req.class);
        ObjValidater.validateReq(req);
    }

}
