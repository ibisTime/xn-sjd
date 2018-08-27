/**
 * @Title XN625200.java 
 * @Package com.cdkj.coin.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月8日 下午3:12:21 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.api.AProcessor;
import com.ogc.standard.dto.res.BooleanRes;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 生成以太坊散取地址（有私钥）
 * @author: haiqingzheng 
 * @since: 2017年11月8日 下午3:12:21 
 * @history:
 */
public class XN802510 extends AProcessor {

    private IEthAddressAO ethAddressAO = SpringContextHolder
        .getBean(IEthAddressAO.class);

    /** 
     * @see com.ogc.standard.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        ethAddressAO.generateMAddress();
        return new BooleanRes(true);
    }

    /** 
     * @see com.ogc.standard.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {
    }

}
