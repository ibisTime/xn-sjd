/**
 * @Title IBtcWAddress.java 
 * @Package com.ogc.standard.ao 
 * @Description 
 * @author taojian  
 * @date 2018年9月11日 下午8:18:44 
 * @version V1.0   
 */
package com.ogc.standard.ao;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.BtcWAddress;

/** 
 * @author: taojian 
 * @since: 2018年9月11日 下午8:18:44 
 * @history:
 */
public interface IBtcWAddressAO {

    // 导入归集地址（无私钥）
    public void importWAddress(String address);

    // 弃用地址
    public void abandon(Long id);

    public Paginable<BtcWAddress> queryBtcWAddressPage(int start, int limit,
            BtcWAddress condition);

}
