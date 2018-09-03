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
import com.ogc.standard.domain.EthMAddress;

/** 
 * @author: haiqingzheng 
 * @since: 2017年10月27日 下午5:38:33 
 * @history:
 */
public interface IEthMAddressAO {

    // 生成散取️地址（有私钥）
    public String generate();

    // 弃用地址
    public void abandon(Long id);

    public Paginable<EthMAddress> queryEthMAddressPage(int start, int limit,
            EthMAddress condition);

}
