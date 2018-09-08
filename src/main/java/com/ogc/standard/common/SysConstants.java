package com.ogc.standard.common;

import java.math.BigDecimal;

public class SysConstants {

    public static final String admin = "admin";

    public static final String COLLECTION_LIMIT = "collection_limit"; // 账户余额大于等于该值时，进行归集

    public static final String WITHDRAW_FEE_ETH = "withdraw_fee_eth"; // ETH提现手续费

    public static final String WITHDRAW_FEE_BTC = "withdraw_fee_btc"; // BTC提现手续费

    public static final String ETH_COIN_PRICE_X = "eth_coin_price_x"; // ETH价格计算因子

    public static final String BTC_COIN_PRICE_X = "btc_coin_price_x"; // BTC价格计算因子

    // 七牛云图片配置
    public static String QINIU_ACCESS_KEY = "qiniu_access_key";

    public static String QINIU_SECRET_KEY = "qiniu_secret_key";

    public static String QINIU_BUCKET = "qiniu_bucket";

    public static String QINIU_DOMAIN = "qiniu_domain";

    // 腾讯云配置
    public static String TX_APP_CODE = "tx_app_code";// 应用编号

    public static String TX_APP_ADMIN = "tx_app_admin";// 应用编号

    public static String TX_ACCESS_KEY = "tx_access_key";// 公钥

    public static String TX_SECRET_KEY = "tx_secret_key";// 私钥

    public static String TX_ACCOUNT_TYPE = "tx_account_type";// 账号类型

    // 取现规则配置
    public static String CUSERQXBS = "CUSERQXBS"; // C端用户取现倍数

    public static String CUSERQXFL = "CUSERQXFL"; // C端用户取现费率

    public static String CUSERQXSX = "CUSERQXSX"; // C端用户取现时效

    public static String CUSERMONTIMES = "CUSERMONTIMES"; // C端用户每月取现次数

    public static String TRANSAMOUNTBS = "TRANSAMOUNTBS"; // C端2C端转账金额倍数

    public static String QXDBZDJE = "QXDBZDJE"; // 取现单笔最大金额

    // 红包规则
    public static String RED_PACKET_NUM_MAX = "red_packet_num_max"; // 最大红包个数

    public static String RED_PACKET_AMOUNT_MAX = "red_packet_amount_max"; // 最大红包总额

    // 短信通知配置
    public static String ZC_SMS_NOTICE = "zc_sms_notice"; // 仲裁短信通知

    public static String QX_SMS_NOTICE = "qx_sms_notice";// 取现短信通知

    // ****** 短信模板 ******

    // 有人提现通知运营人员
    public static String WITHDRAW_CN = "您有一个新的取现订单（编号：%s），请及时处理！";

    // 代注册
    public static String DO_ADD_USER_CN = "尊敬的%s用户，您已成功注册THA钱包，您的默认登录密码是%s，请及时登录平台修改。";

    // 修改手机号
    public static String DO_CHANGE_MOBILE_CN = "尊敬的%s用户，您于%s提交的更改绑定手机号码服务已完成，现绑定手机号码为%s，请妥善保管您的账户相关信息。";

    // 修改登录密码
    public static String DO_MODIFY_LOGIN_PWD_CN = "尊敬的%s用户，您的登录密码修改成功。请妥善保管您的账户相关信息。";

    // 重置登录密码
    public static String DO_RESET_LOGIN_PWD_CN = "尊敬的%s用户，您的登录密码重置成功。请妥善保管您的账户相关信息。";

    // 修改资金密码
    public static String DO_MODIFY_TRADE_PWD_CN = "尊敬的%s用户，您的资金密码修改成功。请妥善保管您的账户相关信息。";

    // 重置资金密码
    public static String DO_RESET_TRADE_PWD_CN = "尊敬的%s用户，您的资金密码重置成功。请妥善保管您的账户相关信息。";

    // 封禁用户
    public static String DO_LOCK_USER_CN = "尊敬的%s用户，您的账号已被管理员封禁，请遵守平台相关规则。";

    // 解禁用户
    public static String DO_UNLOCK_USER_CN = "尊敬的%s用户，您的账号已被管理员解封，请遵守平台相关规则。";

    // 绑定手机
    public static String DO_BIND_MOBILE_CN = "尊敬的%s用户，您于%s提交的绑定手机号码服务已完成，现绑定手机号码为%s，请妥善保管您的账户相关信息。";

    // ****** 币币交易 ******

    public static BigDecimal minCountLimit = new BigDecimal("0.00001"); // 最小委托数量

    public static BigDecimal maxCountLimit = new BigDecimal("1000000"); // 最大委托数量
}
