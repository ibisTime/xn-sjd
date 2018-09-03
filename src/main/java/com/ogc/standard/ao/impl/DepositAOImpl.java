/**
 * @Title DepositAOImpl.java 
 * @Package com.ogc.standard.ao.impl 
 * @Description 
 * @author dl  
 * @date 2018年8月31日 下午4:28:24 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IDepositAO;
import com.ogc.standard.bo.IDepositBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Deposit;

/** 
 * @author: dl 
 * @since: 2018年8月31日 下午4:28:24 
 * @history:
 */
@Service
public class DepositAOImpl implements IDepositAO {

    @Autowired
    private IDepositBO depositBO;

    @Override
    public Deposit getDeposit(String code) {
        return depositBO.getDeposit(code);
    }

    @Override
    public Paginable<Deposit> queryDepositPage(int start, int limit,
            Deposit condition) {
        return depositBO.getPaginable(start, limit, condition);
    }

}
