/**
 * @Title XN625240.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月14日 下午12:40:28 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IArbitrateAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.core.StringValidater;
import com.ogc.standard.domain.Arbitrate;
import com.ogc.standard.dto.req.XN625265Req;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 分页查询仲裁订单
 * @author: haiqingzheng 
 * @since: 2017年11月14日 下午12:40:28 
 * @history:
 */
public class XN625265 extends AProcessor {

    private IArbitrateAO arbitrateAO = SpringContextHolder
        .getBean(IArbitrateAO.class);

    private XN625265Req req;

    /** 
     * @see com.ogc.standard.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        Arbitrate condition = new Arbitrate();
        condition.setCode(req.getCode());
        condition.setTradeOrderCode(req.getTradeOrderCode());
        condition.setYuangao(req.getYuangao());
        condition.setBeigao(req.getBeigao());
        condition.setUpdater(req.getUpdater());
        condition.setOrder("create_datetime", "desc");
        condition.setResult(req.getResult());
        condition.setStatus(req.getStatus());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return arbitrateAO.queryArbitratePage(start, limit, condition);
    }

    /** 
     * @see com.ogc.standard.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN625265Req.class);
        ObjValidater.validateReq(req);
    }

}
