
package com.ogc.standard.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.ogc.standard.ao.IBlacklistAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Blacklist;
import com.ogc.standard.dto.req.XN805245Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 分页查询黑名单
 * @author: dl 
 * @since: 2018年8月20日 下午1:10:20 
 * @history:
 */
public class XN805245 extends AProcessor {

    private IBlacklistAO blacklistAO = SpringContextHolder
        .getBean(IBlacklistAO.class);

    private XN805245Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Blacklist condition = new Blacklist();
        condition.setUserId(req.getUserId());
        condition.setStatus(req.getStatus());
        condition.setType(req.getType());
        condition.setUpdater(req.getUpdater());
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IBlacklistAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return blacklistAO.queryBlacklistPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805245Req.class);
        ObjValidater.validateReq(req);
    }

}
