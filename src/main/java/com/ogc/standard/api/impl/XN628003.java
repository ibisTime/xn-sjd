package com.ogc.standard.api.impl;

import org.apache.commons.collections.CollectionUtils;

import com.ogc.standard.ao.IKeywordAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.dto.req.XN628000Req;
import com.ogc.standard.dto.req.XN628003Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 批量导入关键字
 * @author: xieyj 
 * @since: 2018年8月26日 下午1:56:49 
 * @history:
 */
public class XN628003 extends AProcessor {
    private IKeywordAO keywordAO = SpringContextHolder
        .getBean(IKeywordAO.class);

    private XN628003Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        keywordAO.addKeywords(req.getKeywordList(), req.getUpdater());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams, String userId) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN628003Req.class);
        ObjValidater.validateReq(req);
        if (CollectionUtils.isEmpty(req.getKeywordList())) {
            throw new BizException("xn0000", "关键字列表不能为空");
        } else {
            for (XN628000Req cReq : req.getKeywordList()) {
                StringValidater.validateBlank(cReq.getWord(), cReq.getLevel(),
                    cReq.getReaction());
            }
        }
    }

}
