/**
 * @Title IBtcTransactionAO.java 
 * @Package com.ogc.standard.ao 
 * @Description 
 * @author taojian  
 * @date 2018年9月11日 下午8:46:03 
 * @version V1.0   
 */
package com.ogc.standard.ao;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.BtcTransaction;

/** 
 * @author: taojian 
 * @since: 2018年9月11日 下午8:46:03 
 * @history:
 */

public interface IBtcTransactionAO {

    public Paginable<BtcTransaction> queryBtcTransactionPage(int start,
            int limit, BtcTransaction condition);
}
