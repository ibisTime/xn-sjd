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
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN629762Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 修改关键字(oss)
 * @author: taojian 
 * @since: 2018年11月7日 下午5:29:25 
 * @history:
 */
public class XN629762 extends AProcessor {

    private IKeywordAO keywordAO = SpringContextHolder
        .getBean(IKeywordAO.class);

    private XN629762Req req;

    @Override
    public Object doBusiness() throws BizException {
        keywordAO
            .editKeyword(StringValidater.toInteger(req.getId()), req.getWord(),
                req.getReaction(), req.getRemark(), req.getUpdater());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN629762Req.class);
        ObjValidater.validateReq(req);
    }

}
