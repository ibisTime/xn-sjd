package com.ogc.standard.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    private static Properties props;
    static {
        props = new Properties();
        try {
            props.load(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("config.properties"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("找不到config.properties文件", e);
        } catch (IOException e) {
            throw new RuntimeException("读取config.properties文件出错", e);
        }
    }

    public static String getProperty(String key) {
        if (props == null) {
            // throw new Exception("配置文件初始化失败");
        }
        return props.getProperty(key);
    }

    public static final class Config {
        public static String SMS_URL = props.getProperty("SMS_URL");

        public static String ALIPAY_APPID = props.getProperty("alipay.appid");

        public static String ALIPAY_PRIVATEKEY = props
            .getProperty("alipay.privatekey");

        public static String ALIPAY_PUBLICKEY = props
            .getProperty("alipay.publickey");

        public static String ALIPAY_NOTIFYURL = props
            .getProperty("alipay.notifyurl");

        public static String ALIPAY_RETURNURL = props
            .getProperty("alipay.returnurl");

        public static String ALIPAY_SIGNTYPE = props
            .getProperty("alipay.signtype");

        public static String ALIPAY_GATEWAY = props
            .getProperty("alipay.gateway");

        public static String ALIPAY_PROVIDERID = props
            .getProperty("alipay.providerid");

        public static String WECHAT_H5_BACKURL = props
            .getProperty("WECHAT_H5_BACKURL");

        public static String WECHAT_REFUND_URL = props
            .getProperty("WECHAT_REFUND_URL");

        public static String KDNIAO_EBUSINESSID = props
            .getProperty("kdniao.EBusinessID");

        public static String KDNIAO_APPKEY = props.getProperty("kdniao.AppKey");

        public static String KDNIAO_REQURL = props.getProperty("kdniao.ReqURL");
    }
}
