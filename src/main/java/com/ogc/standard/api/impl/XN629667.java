package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.INotifyUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.NotifyUser;
import com.ogc.standard.dto.req.XN629667Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 分页查审核通知人
 * @author: silver 
 * @since: Jan 17, 2019 4:00:32 PM 
 * @history:
 */
public class XN629667 extends AProcessor {
    private INotifyUserAO notifyUserAO = SpringContextHolder
        .getBean(INotifyUserAO.class);

    private XN629667Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        NotifyUser condition = new NotifyUser();
        condition.setType(req.getType());
        condition.setName(req.getName());
        condition.setMobile(req.getMobile());

        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = INotifyUserAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        return notifyUserAO.queryNotifyUserList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629667Req.class);
        ObjValidater.validateReq(req);
    }
}
