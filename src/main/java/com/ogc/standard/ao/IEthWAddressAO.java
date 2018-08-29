/**
 * @Title IEthAddressAO.java 
 * @Package com.cdkj.coin.ao 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年10月27日 下午5:38:33 
 * @version V1.0   
 */
package com.ogc.standard.ao;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.EthWAddress;

/** 
 * @author: haiqingzheng 
 * @since: 2017年10月27日 下午5:38:33 
 * @history:
 */
public interface IEthWAddressAO {

    // 导入归集地址（无私钥）
    public void importWAddress(String address);

    // 弃用地址
    public void abandon(Long id);

    public Paginable<EthWAddress> queryEthWAddressPage(int start, int limit,
            EthWAddress condition);

}
