package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IGiveTreeRecordAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.DateUtil;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.GiveTreeRecord;
import com.ogc.standard.dto.req.XN629337Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查询赠送记录
 * @author: jiafr 
 * @since: 2018年9月28日 下午2:24:15 
 * @history:
 */
public class XN629337 extends AProcessor {

    private IGiveTreeRecordAO giveTreeRecordAO = SpringContextHolder
        .getBean(IGiveTreeRecordAO.class);

    private XN629337Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        GiveTreeRecord condition = new GiveTreeRecord();
        condition.setAdoptTreeCode(req.getAdoptTreeCode());
        condition.setUserId(req.getUserId());
        condition.setToUserId(req.getToUserId());
        condition.setCreateDatetime(DateUtil.strToDate(req.getCreateDatetime(),
            DateUtil.DATA_TIME_PATTERN_1));

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IGiveTreeRecordAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return giveTreeRecordAO.queryGiveTreeRecordList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629337Req.class);
        ObjValidater.validateReq(req);
    }
}
