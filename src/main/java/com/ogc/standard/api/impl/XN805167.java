/**
 * @Title XN805160.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年9月13日 下午7:36:43 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IUserIdAuthAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN805167Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 根据用户查询认证记录
 * @author: taojian 
 * @since: 2018年9月13日 下午7:36:43 
 * @history:
 */
public class XN805167 extends AProcessor {

    private IUserIdAuthAO userIdAuthAO = SpringContextHolder
        .getBean(IUserIdAuthAO.class);

    private XN805167Req req;

    @Override
    public Object doBusiness() throws BizException {
        return userIdAuthAO.getUserIdAuthByUserId(req.getApplyUser());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805167Req.class);
        ObjValidater.validateReq(req);
    }

}
