/**
 * @Title XN629760.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月7日 下午5:29:25 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IKeywordAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN629760Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 新增关键字
 * @author: taojian 
 * @since: 2018年11月7日 下午5:29:25 
 * @history:
 */
public class XN629760 extends AProcessor {

    private IKeywordAO keywordAO = SpringContextHolder
        .getBean(IKeywordAO.class);

    private XN629760Req req;

    @Override
    public Object doBusiness() throws BizException {
        keywordAO.addKeyword(req.getWord(), req.getReaction(), req.getRemark(),
            req.getUpdater());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629760Req.class);
        ObjValidater.validateReq(req);
    }

}
