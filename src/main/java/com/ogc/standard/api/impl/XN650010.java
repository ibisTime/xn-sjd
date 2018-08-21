package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IGroupCoinAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN650010Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 详情查询组合币种配置（详细仓位）（front）
 * @author: lei 
 * @since: 2018年8月20日 下午9:34:20 
 * @history:
 */
public class XN650010 extends AProcessor {

    private IGroupCoinAO groupCoinAO = SpringContextHolder
        .getBean(IGroupCoinAO.class);

    private XN650010Req req = null;

    @Override
    public Object doBusiness() throws BizException {

        return groupCoinAO.queryGroupCoinListByGroupCode(req.getCode());
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN650010Req.class);
        ObjValidater.validateReq(req);

    }

}
