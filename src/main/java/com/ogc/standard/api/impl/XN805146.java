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
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.SignLog;
import com.ogc.standard.dto.req.XN805146Req;
import com.ogc.standard.enums.ESignLogType;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询连续签到
 * @author: silver 
 * @since: Jan 22, 2019 9:55:36 PM 
 * @history:
 */
public class XN805146 extends AProcessor {
    private ISignLogAO signLogAO = SpringContextHolder
        .getBean(ISignLogAO.class);

    private XN805146Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SignLog condition = new SignLog();
        condition.setUserId(req.getUserId());
        condition.setType(ESignLogType.SIGN_IN.getCode());

        condition.setCreateStartDatetime(DateUtil.strToDate(
            req.getCreateStartDatetime(), DateUtil.FRONT_DATE_FORMAT_STRING));
        condition.setCreateEndDatetime(DateUtil.strToDate(
            req.getCreateEndDatetime(), DateUtil.FRONT_DATE_FORMAT_STRING));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = ISignLogAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return signLogAO.queryContinueSignLogList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805146Req.class);
        ObjValidater.validateReq(req);
    }

}
