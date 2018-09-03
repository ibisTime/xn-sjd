/**
 * @Title EthXAddressAOImpl.java 
 * @Package com.cdkj.coin.ao.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年10月27日 下午5:43:34 
 * @version V1.0   
 */
package com.ogc.standard.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ogc.standard.ao.IEthXAddressAO;
import com.ogc.standard.bo.IEthXAddressBO;
import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.EthXAddress;

/** 
 * @author: haiqingzheng 
 * @since: 2017年10月27日 下午5:43:34 
 * @history:
 */
@Service
public class EthXAddressAOImpl implements IEthXAddressAO {

    @Autowired
    private IEthXAddressBO ethXAddressBO;

    @Override
    public Paginable<EthXAddress> queryEthXAddressPage(int start, int limit,
            EthXAddress condition) {
        Paginable<EthXAddress> results = ethXAddressBO.getPaginable(start,
            limit, condition);
        return results;
    }

}
