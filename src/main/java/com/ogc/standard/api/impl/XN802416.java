package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IDivideDetailAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.DivideDetail;
import com.ogc.standard.dto.req.XN802416Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 分红明细分页查询
 * @author: lei 
 * @since: 2018年9月15日 下午5:12:07 
 * @history:
 */
public class XN802416 extends AProcessor {

    private IDivideDetailAO divideDetailAO = SpringContextHolder
        .getBean(IDivideDetailAO.class);

    private XN802416Req req;

    @Override
    public Object doBusiness() throws BizException {

        DivideDetail condition = new DivideDetail();
        condition.setDivideId(req.getDivideId());
        condition.setUserId(req.getUserId());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return divideDetailAO.queryDivideDetailPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802416Req.class);
        ObjValidater.validateReq(req);
    }

}
