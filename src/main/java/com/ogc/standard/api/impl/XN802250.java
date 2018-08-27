/**
 * @Title XN802250.java 
 * @Package com.cdkj.coin.wallet.api.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2018年3月13日 上午11:12:25 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.cdkj.coin.wallet.ao.ICoinAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.common.JsonUtil;
import com.ogc.standard.core.ObjValidater;
import com.ogc.standard.dto.req.XN802000Req;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 新增token币
 * @author: haiqingzheng 
 * @since: 2018年3月13日 上午11:12:25 
 * @history:
 */
public class XN802250 extends AProcessor {

    private ICoinAO coinAO = SpringContextHolder.getBean(ICoinAO.class);

    private XN802000Req req = null;

    /** 
     * @see com.cdkj.coin.wallet.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        synchronized (XN802250.class) {
            coinAO.addCoinAndPublish(req);
        }
        return new BooleanRes(true);
    }

    /** 
     * @see com.cdkj.coin.wallet.api.IProcessor#doCheck(java.lang.String, java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802000Req.class);
        ObjValidater.validateReq(req);
    }

}
