/**
 * @Title IBtcMAddress.java 
 * @Package com.ogc.standard.ao 
 * @Description 
 * @author taojian  
 * @date 2018年9月11日 下午3:20:49 
 * @version V1.0   
 */
package com.ogc.standard.ao;

import com.ogc.standard.bo.base.Paginable;
import com.ogc.standard.domain.BtcMAddress;

/** 
 * @author: taojian 
 * @since: 2018年9月11日 下午3:20:49 
 * @history:
 */
public interface IBtcMAddressAO {

    // 生成散取地址
    public String addAddress();

    // 弃用地址
    public void abandon(Long id);

    public Paginable<BtcMAddress> queryBtcMAddressPage(int start, int limit,
            BtcMAddress condition);
}
