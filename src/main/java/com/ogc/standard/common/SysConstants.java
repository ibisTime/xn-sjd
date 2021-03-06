package com.ogc.standard.common;

public class SysConstants {
    // ip
    public static String IP = "192.168.1.1";

    // 七牛云图片配置
    public static String QINIU_ACCESS_KEY = "qiniu_access_key";

    public static String QINIU_SECRET_KEY = "qiniu_secret_key";

    public static String QINIU_BUCKET = "qiniu_bucket";

    public static String QINIU_DOMAIN = "qiniu_domain";

    // 微信公众号配置
    public static String WX_H5_ACCESS_KEY = "wx_h5_access_key";

    public static String WX_H5_SECRET_KEY = "wx_h5_secret_key";

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
    public static String JF_DK_MAX_RATE = "JF_DK_MAX_RATE"; // 订单可用积分抵扣比例

    public static final String CNY2JF_RATE = "CNY2JF_RATE"; // 积分兑换人民币比例

    public static final String ADOPT_DK_RATE = "ADOPT_DK_RATE"; // 认养消耗汇率

    public static final String PRESELL_DK_RATE = "PRESELL_DK_RATE"; // 预售消耗汇率

    public static final String COMMODITY_DK_RATE = "COMMODITY_DK_RATE"; // 商城消耗汇率

    // 认养分销
    public static String DIST_OWENER_RATE = "DIST_OWENER_RATE"; // 产权方的总额

    public static String DIST_MAINTAIN_RATE = "DIST_MAINTAIN_RATE"; // 养护方的总额

    public static String DIST_PLAT_RATE = "DIST_PLAT_RATE"; // 平台方的总额

    public static String DIST_AGENT_RATE = "DIST_AGENT_RATE"; // 代理方的总额

    public static String DIST_USER_BACK_JF_RATE = "DIST_USER_BACK_JF_RATE"; // 用户返积分的总额

    public static String ADOPT_DIRECT = "ADOPT_DIRECT"; // 用户直推认养送

    public static String ADOPT_INDIRECT = "ADOPT_INDIRECT"; // 用户间推认养送

    // 预售分销
    public static String PRESELL_DIST_OWENER_RATE = "PRESELL_DIST_OWENER_RATE"; // 产权方的总额

    public static String PRESELL_DIST_MAINTAIN_RATE = "PRESELL_DIST_MAINTAIN_RATE"; // 养护方的总额

    public static String PRESELL_DIST_PLAT_RATE = "PRESELL_DIST_PLAT_RATE"; // 平台方的总额

    public static String PRESELL_DIST_AGENT_RATE = "PRESELL_DIST_AGENT_RATE"; // 代理方的总额

    public static String PRESELL_DIST_USER_BACK_JF_RATE = "PRESELL_DIST_USER_BACK_JF_RATE"; // 用户返积分的总额

    public static String PRESELL_DIRECT = "PRESELL_DIRECT"; // 用户直推预售送

    public static String PRESELL_INDIRECT = "PRESELL_INDIRECT"; // 用户间推预售送

    // 商城分销
    public static String COMMODITY_DIST_BUSINESS_RATE = "COMMODITY_DIST_BUSINESS_RATE"; // 商家的总额

    public static String COMMODITY_DIST_USER_BACK_JF_RATE = "COMMODITY_DIST_USER_BACK_JF_RATE"; // 用户返积分的总额

    public static String COMMODITY_DIST_AGENT_RATE = "COMMODITY_DIST_AGENT_RATE"; // 代理方的总额

    public static String COMMODITY_DIST_PLAT_RATE = "COMMODITY_DIST_PLAT_RATE"; // 平台方的总额

    public static String COMMODITY_DIRECT = "COMMODITY_DIRECT"; // 用户直推商城送

    public static String COMMODITY_INDIRECT = "COMMODITY_INDIRECT"; // 用户间推商城送

    // 代理等级分成
    public static String DIST_AGENT1_RATE = "DIST_AGENT1_RATE"; // 一级代理分成比例

    public static String DIST_AGENT2_RATE = "DIST_AGENT2_RATE"; // 二级代理分成比例

    public static String DIST_AGENT3_RATE = "DIST_AGENT3_RATE"; // 三级代理分成比例

    public static String DIST_AGENT4_RATE = "DIST_AGENT4_RATE"; // 四级代理分成比例

    public static final String WEIGHT_RATE1 = "WEIGHT_RATE1"; // 好友权重比例1

    public static final String WEIGHT_RATE2 = "WEIGHT_RATE2"; // 好友权重比例2

    // 碳泡泡规则
    public static final String PRESENT_TPP = "PRESENT_TPP_QUANTITY";// 赠送碳泡泡记录

    public static final String SIGN_TPP = "SIGN_TPP";// 签到

    public static final String CONTINUE_SIGN_RATE = "CONTINUE_SIGN_RATE";// 连续3天签到

    public static final String SHARE = "SHARE";// 分享

    public static final String CREATE_TPP_RATE = "CREATE_TPP_RATE";// 碳泡泡产生比例

    public static final String ADOPT_CREATE_TPP_RATE = "CREATE_TPP_RATE";// 碳泡泡产生比例

    public static final String PRESELL_CREATE_TPP_RATE = "CREATE_TPP_RATE";// 碳泡泡产生比例

    public static final String TPP_EXPIRE_HOUR = "TPP_EXPIRE_HOUR";// 碳泡泡过期时间

    public static final String OTHER_TAKE_MAX_QUANTITY = "OTHER_TAKE_MAX_QUANTITY";// 每天最多被偷取数量

    // 积分规则
    public static final String REGISTER = "REGISTER";// 注册送积分

    public static final String REAL_NAME = "REAL_NAME";// 实名送积分

    public static final String BIND_MOBILE = "BIND_MOBILE";// 绑定手机号

    public static final String BIND_EMAIL = "BIND_EMAIL";// 绑定邮箱

    public static final String UPLOAD_PHOTO = "UPLOAD_PHOTO";// 上传头像

    public static final String COMPLETE_INFO = "COMPLETE_INFO";// 完善用户信息

    public static final String SIGN_JF = "SIGN_JF";// 签到

    public static final String CONTINUE_LOGIN_RATE = "CONTINUE_LOGIN_RATE";// 连续登录返积分比例

    public static final String INVITE_USER = "INVITE_USER";// 邀请好友注册

    public static final String BACK_JFPOOL_RATE = "BACK_JFPOOL_RATE";// 返积分池汇率

    public static final String ADOPT_CREATE_RATE = "ADOPT_CREATE_RATE";// 认养产生汇率

    public static final String PRESELL_CREATE_RATE = "PRESELL_CREATE_RATE";// 预售产生汇率

    public static final String COMMODITY_CREATE_RATE = "COMMODITY_CREATE_RATE";// 商城产生汇率

    // 用户等级
    public static final String USER_LEVEL_0 = "USER_LEVEL_0";// 初探翠林

    public static final String USER_LEVEL_1 = "USER_LEVEL_1";// 护树新秀

    public static final String USER_LEVEL_2 = "USER_LEVEL_2";// 护树高手

    public static final String USER_LEVEL_3 = "USER_LEVEL_3";// 育树林丰

    public static final String USER_LEVEL_4 = "USER_LEVEL_4";// 愈林诗人

    public static final String USER_LEVEL_5 = "USER_LEVEL_5";// 爱林天使

    // 识别码
    public static final String ID_INVALID_HOURS = "ID_INVALID_HOURS";// 识别码失效时间

    // 商城
}
