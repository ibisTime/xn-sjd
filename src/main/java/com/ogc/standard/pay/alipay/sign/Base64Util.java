/**
 * @Title Base64Util.java 
 * @Package com.alipay.sign 
 * @Description 
 * @author xieyj  
 * @date 2017年11月12日 下午2:04:07 
 * @version V1.0   
 */
package com.ogc.standard.pay.alipay.sign;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 将String进行base64编码解码，使用utf-8
 */
public class Base64Util {

    private static final Logger logger = LoggerFactory
        .getLogger(Base64Util.class);

    private static final String UTF_8 = "UTF-8";

    /**
     * 对给定的字符串进行base64解码操作
     */
    public static String decode(String inputData) {
        try {
            if (null == inputData) {
                return null;
            }
            return new String(Base64.decodeBase64(inputData.getBytes(UTF_8)),
                UTF_8);
        } catch (UnsupportedEncodingException e) {
            logger.error(inputData, e);
        }

        return null;
    }

    /**
     * 对给定的字符串进行base64加密操作
     */
    public static String encode(String inputData) {
        try {
            if (null == inputData) {
                return null;
            }
            return new String(Base64.encodeBase64(inputData.getBytes(UTF_8)),
                UTF_8);
        } catch (UnsupportedEncodingException e) {
            logger.error(inputData, e);
        }

        return null;
    }
}
