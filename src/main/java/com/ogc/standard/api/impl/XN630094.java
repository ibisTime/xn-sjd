/**
 * @Title XN630094.java 
 * @Package com.ogc.standard.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年9月12日 下午9:23:08 
 * @version V1.0   
 */
package com.ogc.standard.api.impl;

import com.ogc.standard.ao.IGoogleAuthAO;
import com.ogc.standard.api.AProcessor;
import com.ogc.standard.dto.res.XN630094Res;
import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.ParaException;
import com.ogc.standard.spring.SpringContextHolder;

/** 
 * 获取谷歌验证码
 * @author: taojian 
 * @since: 2018年9月12日 下午9:23:08 
 * @history:
 */
public class XN630094 extends AProcessor {

    private IGoogleAuthAO googleAuthAO = SpringContextHolder
        .getBean(IGoogleAuthAO.class);

    @Override
    public Object doBusiness() throws BizException {
        String secret = googleAuthAO.generateSecretKey();
        return new XN630094Res(secret);
    }

    @Override
    public void doCheck(String inputparams, String operator)
            throws ParaException {

    }

}
