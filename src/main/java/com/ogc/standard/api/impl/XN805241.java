/**
 * @Title XN805205.java 
 * @Package com.std.user.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月23日 下午2:53:59 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IBlacklistAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN805241Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 移除黑名单
 * @author: dl 
 * @since: 2018年8月20日 下午1:09:32 
 * @history:
 */
public class XN805241 extends AProcessor {

    private IBlacklistAO blacklistAO = SpringContextHolder
        .getBean(IBlacklistAO.class);

    private XN805241Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return blacklistAO.editBlacklist(StringValidater.toLong(req.getId()),
            req.getUpdater(), req.getRemark());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805241Req.class);
        ObjValidater.validateReq(req);
    }

}
