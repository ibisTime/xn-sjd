/**
 * @Title XN802900.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年12月7日 上午10:21:31 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.api.AProcessor;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.http.BizConnecter;

/** 
 * @author: haiqingzheng 
 * @since: 2017年12月7日 上午10:21:31 
 * @history:
 */
public class XN802480 extends AProcessor {

    /** 
     * @see com.ogc.standard.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        return BizConnecter.getBizData("802480", "{}", Object.class);
    }

    /** 
     * @see com.ogc.standard.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {

    }

}
