/**
 * @Title SymbolUtil.java 
 * @Package com.cdkj.info.util 
 * @Description 
 * @author haiqingzheng  
 * @date 2018年4月18日 下午7:50:25 
 * @version V1.0   
 */
package com.ogc.standard.util;

/** 
 * 
 * @author: lei 
 * @since: 2018年8月22日 下午8:18:52 
 * @history:
 */
public class SymbolUtil {

    public static String getSymbolPair(String symbol, String toSymbol) {
        return symbol.toUpperCase() + "/" + toSymbol.toUpperCase();
    }

    public static String getSymbol(String symbolPair) {
        String[] symbols = symbolPair.split("/");
        return symbols[0];
    }

    public static String getToSymbol(String symbolPair) {
        String[] symbols = symbolPair.split("/");
        return symbols[1];
    }

    public static String getOkexSymbolPair(String symbolPair) {
        String[] symbols = symbolPair.split("/");
        return symbols[0] + "_" + symbols[1];
    }

}
