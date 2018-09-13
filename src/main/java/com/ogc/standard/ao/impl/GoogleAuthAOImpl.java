/**
 * @Title GoogleAuthAOImpl.java 
 * @Package com.ogc.standard.ao.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年12月6日 下午4:42:29 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IGoogleAuthAO;
import com.ogc.standard.bo.IGoogleAuthBO;

/** 
 * @author: haiqingzheng 
 * @since: 2017年12月6日 下午4:42:29 
 * @history:
 */
@Service
public class GoogleAuthAOImpl implements IGoogleAuthAO {

    @Autowired
    private IGoogleAuthBO googleAuthBO;

    @Override
    public String generateSecretKey() {
        return googleAuthBO.generateSecretKey();
    }

}
