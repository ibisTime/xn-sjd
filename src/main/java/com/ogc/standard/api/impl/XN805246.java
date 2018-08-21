/**
 * @Title XN805426.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author dl  
 * @date 2018年8月20日 下午1:25:18 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IBlacklistAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.domain.Blacklist;
import com.ogc.standard.dto.req.XN805246Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 列表查询黑名单
 * @author: dl 
 * @since: 2018年8月20日 下午1:25:18 
 * @history:
 */
public class XN805246 extends AProcessor {
    private IBlacklistAO blacklistAO = SpringContextHolder
        .getBean(IBlacklistAO.class);

    private XN805246Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Blacklist condition = new Blacklist();
        condition.setUserId(req.getUserId());
        condition.setStatus(req.getStatus());
        condition.setType(req.getType());
        condition.setUpdater(req.getUpdater());
        return blacklistAO.queryBlacklistList(condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805246Req.class);
        ObjValidater.validateReq(req);

    }

}
