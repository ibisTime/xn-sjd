/**
 * @Title XN805095.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年9月13日 上午11:06:30 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IUserAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN805090Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 更改手续费率
 * @author: taojian 
 * @since: 2018年9月13日 上午11:06:30 
 * @history:
 */
public class XN805090 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805090Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.editTradeRate(req.getUserId(),
            StringValidater.toDouble(req.getTradRate()));
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805090Req.class);
        ObjValidater.validateReq(req);
    }

}
