/**
 * @Title IDpositAO.java 
 * @Package com.ogc.standard.ao 
 * @Description 
 * @author dl  
 * @date 2018年8月31日 下午3:13:35 
 * @version V1.0   
 */
package com.ogc.standard.ao;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.Deposit;

/** 
 * @author: dl 
 * @since: 2018年8月31日 下午3:13:35 
 * @history:
 */
public interface IDepositAO {
    String DEFAULT_ORDER_COLUMN = "code";

    public Deposit getDeposit(String code);

    public Paginable<Deposit> queryDepositPage(int start, int limit,
            Deposit condition);
}
