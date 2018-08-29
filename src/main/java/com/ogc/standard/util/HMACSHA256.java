package com.ogc.standard.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

public class HMACSHA256 {

    /**     
    * 将加密后的字节数组转换成字符串   
    *    
    * @param b 字节数组    
    * @return 字符串     
    */
    private static String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toLowerCase();
    }

    /**   
     * sha256_HMAC加密    
     * @param message 消息   
     * @param secret  秘钥 
     * @return 加密后字符串  
     */
    public static String sha256_HMAC(String message, String secret) {
        String hash = "";
        try {
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(),
                "HmacSHA256");
            sha256Hmac.init(secretKey);
            byte[] bytes = sha256Hmac.doFinal(message.getBytes());
            hash = Base64.encodeBase64String(bytes);
        } catch (Exception e) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "HmacSHA256签名发送错误，原因:" + e.getMessage());
        }
        return hash;
    }

}
