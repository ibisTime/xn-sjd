/**
 * @Title IDepositBO.java 
 * @Package com.ogc.standard.bo 
 * @Description 
 * @author dl  
 * @date 2018年8月31日 下午3:01:19 
 * @version V1.0   
 */
package com.ogc.standard.bo;

import com.ogc.standard.bo.base.IPaginableBO;
import com.ogc.standard.domain.Deposit;

/** 
 * @author: dl 
 * @since: 2018年8月31日 下午3:01:19 
 * @history:
 */
public interface IDepositBO extends IPaginableBO<Deposit> {

    public Deposit getDeposit(String code);

}
