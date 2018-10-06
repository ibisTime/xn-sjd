package com.ogc.standard.pay.alipay.config;

import com.ogc.standard.common.PropertiesUtil;

public class AlipayConfig {
    // 商户appid
    public static String APPID = PropertiesUtil.Config.ALIPAY_APPID;

    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = PropertiesUtil.Config.ALIPAY_PRIVATEKEY;

    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String NOTIFY_URL = PropertiesUtil.Config.ALIPAY_NOTIFYURL;

    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 商户可以自定义同步跳转地址
    public static String RETURN_URL = PropertiesUtil.Config.ALIPAY_RETURNURL;

    public static String PROVIDER_ID = PropertiesUtil.Config.ALIPAY_PROVIDERID;

    // 请求网关地址
    public static String URL = PropertiesUtil.Config.ALIPAY_GATEWAY;

    // 编码
    public static String CHARSET = "UTF-8";

    // 返回格式
    public static String FORMAT = "json";

    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = PropertiesUtil.Config.ALIPAY_PUBLICKEY;

    // 日志记录目录
    public static String LOG_PATH = "/log";

    // RSA2
    public static String SIGNTYPE = PropertiesUtil.Config.ALIPAY_SIGNTYPE;

}
