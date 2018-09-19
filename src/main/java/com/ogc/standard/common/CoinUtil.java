package com.ogc.standard.common;

import java.math.BigDecimal;

/** 
 * @author: haiqingzheng 
 * @since: 2018年3月13日 上午11:36:14 
 * @history:
 */
public class CoinUtil {

    public static BigDecimal fromMinUnit(BigDecimal orgNum, Integer unit) {
        return orgNum.divide(BigDecimal.TEN.pow(unit));
    }

    public static BigDecimal toMinUnit(BigDecimal orgNum, Integer unit) {
        return orgNum.multiply(BigDecimal.TEN.pow(unit));
    }
}
