/**
 * @Title XN625240.java
 * @Package com.ogc.standard.api.impl
 * @Description
 * @author leo(haiqing)
 * @date 2017年11月14日 下午12:40:28
 * @version V1.0
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.ITradeOrderAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN625244Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.enums.EReleaserKind;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/**
 * 卖家释放
 * @author: haiqingzheng
 * @since: 2017年11月14日 下午12:40:28 
 * @history:
 */
public class XN625244 extends AProcessor {

    private ITradeOrderAO tradeOrderAO = SpringContextHolder
        .getBean(ITradeOrderAO.class);

    private XN625244Req req;

    @Override
    public Object doBusiness() throws BizException {

        tradeOrderAO.release(req.getCode(), req.getUpdater(), "卖家已释放",
            EReleaserKind.seller.getCode());
        return new BooleanRes(true);

    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN625244Req.class);
        req.setUpdater(operator);
        ObjValidater.validateReq(req);
    }

}
