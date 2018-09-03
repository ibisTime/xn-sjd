/**
 * @Title BTCUtil.java 
 * @Package com.cdkj.coin.common 
 * @Description 
 * @author haiqingzheng  
 * @date 2018年2月23日 上午11:14:16 
 * @version V1.0   
 */
package com.ogc.standard.common;

import java.math.BigDecimal;

/** 
 * @author: haiqingzheng 
 * @since: 2018年2月23日 上午11:14:16 
 * @history:
 */
public class BTCUtil {
    /**
     * 100000000sta 转换为 1btc
     *
     * @param orgNum
     * @return
     */
    public static BigDecimal fromBtc(BigDecimal orgNum) {
        return orgNum.divide(BigDecimal.TEN.pow(8));
    }

    public static BigDecimal toBtc(BigDecimal orgNum) {
        return orgNum.multiply(BigDecimal.TEN.pow(8));
    }

    public static void main(String[] args) {
        System.out.println(
            new BigDecimal("376131082").compareTo(toBtc(BigDecimal.ONE)) < 0);

    }

}
