/**
 * @Title IAlipayAO.java 
 * @Package com.std.account.ao 
 * @Description 
 * @author haiqingzheng  
 * @date 2017年1月11日 下午8:55:34 
 * @version V1.0   
 */
package com.ogc.standard.bo;

import java.math.BigDecimal;

/** 
 * @author: haiqingzheng 
 * @since: 2017年1月11日 下午8:55:34 
 * @history:
 */
public interface IAlipayBO {
    // 支付宝h5支付，第一步：获取签名后的订单信息。
    public Object getSignedH5Order(String applyUser, String toUser,
            String payGroup, String bizType, String bizNote,
            BigDecimal transAmount);

    public void doCallbackH5(String result);

}
