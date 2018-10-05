package com.ogc.standard.common;

public class SysConstants {

    // 七牛云图片配置
    public static String QINIU_ACCESS_KEY = "qiniu_access_key";

    public static String QINIU_SECRET_KEY = "qiniu_secret_key";

    public static String QINIU_BUCKET = "qiniu_bucket";

    public static String QINIU_DOMAIN = "qiniu_domain";

    // 取现规则配置
    public static String USERQXBS = "USERQXBS"; // C端用户取现倍数

    public static String USERQXFL = "USERQXFL"; // C端用户取现费率

    public static String USERQXSX = "USERQXSX"; // C端用户取现时效

    public static String USERMONTIMES = "USERMONTIMES"; // C端用户每月取现次数

    public static String QXDBZDJE = "QXDBZDJE"; // 取现单笔最大金额

    // 短信模板
    // 有人提现通知运营人员
    public static String WITHDRAW_CN = "您有一个新的取现订单（编号：%s），请及时处理！";

    // 代注册
    public static String DO_ADD_USER_CN = "尊敬的%s用户，您已成功注册，您的默认登录密码是%s，请及时登录平台修改。";

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

    // 抵扣积分
    public static String JF_DK_MAX_RATE = "JF_DK_MAX_RATE"; // 积分抵扣最大比例

    public static final String CNY2JF_RATE = "CNY2JF_RATE"; // 积分兑换人民币比例

    // 认养分销
    public static String DIST_OWENER_RATE = "DIST_OWENER_RATE"; // 产权方的总额

    public static String DIST_MAINTAIN_RATE = "DIST_MAINTAIN_RATE"; // 养护方的总额

    public static String DIST_PLAT_RATE = "DIST_PLAT_RATE"; // 平台方的总额

    public static String DIST_AGENT_RATE = "DIST_AGENT_RATE"; // 代理方的总额

    public static String DIST_USER_BACK_JF_RATE = "DIST_USER_BACK_JF_RATE"; // 用户返积分的总额

    // 代理等级分成
    public static String DIST_AGENT1_RATE = "DIST_AGENT1_RATE"; // 一级代理分成比例

    public static String DIST_AGENT2_RATE = "DIST_AGENT2_RATE"; // 二级代理分成比例

    public static String DIST_AGENT3_RATE = "DIST_AGENT3_RATE"; // 三级代理分成比例

    public static String DIST_AGENT4_RATE = "DIST_AGENT4_RATE"; // 四级代理分成比例

    public static final String WEIGHT_RATE1 = "WEIGHT_RATE1"; // 好友权重比例1

    public static final String WEIGHT_RATE2 = "WEIGHT_RATE2"; // 好友权重比例2

}
