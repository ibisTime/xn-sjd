package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IMaintainRecordAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.MaintainRecord;
import com.ogc.standard.dto.req.XN629637Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 列表查询养护记录
 * @author: silver 
 * @since: 2018年9月29日 上午10:49:25 
 * @history:
 */
public class XN629637 extends AProcessor {
    private IMaintainRecordAO maintainRecordAO = SpringContextHolder
        .getBean(IMaintainRecordAO.class);

    private XN629637Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        MaintainRecord condition = new MaintainRecord();
        condition.setTreeNumber(req.getTreeNumber());
        condition.setProjectCode(req.getProjectCode());
        condition.setMaintainerCode(req.getMaintainerCode());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IMaintainRecordAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return maintainRecordAO.queryMaintainRecordList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629637Req.class);
        ObjValidater.validateReq(req);
    }
}
