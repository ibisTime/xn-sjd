package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IWithdrawAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629903Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 提现金额统计
 * @author: silver 
 * @since: Oct 22, 2018 3:52:28 PM 
 * @history:
 */
public class XN629903 extends AProcessor {
    private IWithdrawAO withdrawAO = SpringContextHolder
        .getBean(IWithdrawAO.class);

    private XN629903Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return withdrawAO.getTotalWithdraw(req.getApplyUser(),
            req.getStatusList());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629903Req.class);
        ObjValidater.validateReq(req);
    }
}
