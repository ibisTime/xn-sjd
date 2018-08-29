package com.ogc.standard.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.ogc.standard.exception.BizException;
import com.ogc.standard.exception.EBizErrorCode;

/** 
 * @author: haiqingzheng 
 * @since: 2018年4月13日 下午3:13:07 
 * @history:
 */
public class URLEncodeUtil {

    public static String encode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "URIEncode发送错误原因" + e.getMessage());
        }
    }

}
