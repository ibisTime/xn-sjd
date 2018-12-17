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
    public String getSignedOrder(String applyUser, String userId,
            String payGroup, String bizType, String bizNote,
            BigDecimal transAmount);

    // 支付宝退款
    public String doRefund(String refNo, String bizType, String bizNote,
            BigDecimal refundAmount);

}
