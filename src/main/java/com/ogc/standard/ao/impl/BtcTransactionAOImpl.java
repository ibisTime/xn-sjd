/**
 * @Title BtcTransactionAOImpl.java 
 * @Package com.ogc.standard.ao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年9月11日 下午8:50:26 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IBtcTransactionAO;
import com.ogc.standard.bo.IBtcTransactionBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.BtcTransaction;

/** 
 * @author: taojian 
 * @since: 2018年9月11日 下午8:50:26 
 * @history:
 */
@Service
public class BtcTransactionAOImpl implements IBtcTransactionAO {

    @Autowired
    private IBtcTransactionBO btcTransactionBO;

    @Override
    public Paginable<BtcTransaction> queryBtcTransactionPage(int start,
            int limit, BtcTransaction condition) {
        return btcTransactionBO.getPaginable(start, limit, condition);
    }

}
