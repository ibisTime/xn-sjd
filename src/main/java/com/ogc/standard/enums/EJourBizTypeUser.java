package com.ogc.standard.enums;

import java.util.HashMap;
import java.util.Map;

import com.ogc.standard.exception.BizException;

/**
 * @author: xieyj 
 * @since: 2018年10月2日 下午10:52:13 
 * @history:
 */
public enum EJourBizTypeUser {

    // 人民币账户
    CHARGE("charge", "充值"),

    WITHDRAW("withdraw", "取现"),

    WITHDRAW_FEE("withdraw_fee", "取现手续费"),

    WITHDRAW_FROZEN("withdraw_frozen", "取现冻结"),

    WITHDRAW_UNFROZEN("withdraw_unfrozen", "取现解冻"),

    HC("hc", "红冲"),

    LB("lb", "蓝补"),

    ADOPT("adopt", "认养"),

    ADOPT_COLLECT("adopt_collect", "集体认养"),

    // 碳泡泡账户
    ADOPT_DAY_BACK("adopt_day_back", "认养消费每日收取碳泡泡"),

    SHARE("share", "分享"),

    PRESENT("present", "赠送"),

    SIGN("sign", "签到"),

    // 积分账户
    REGIST("reg", "注册送积分"),

    BIND_MOBILE("bind_mobile", "绑定手机"),

    BIND_email("bind_email", "绑定邮箱"),

    UPLOAD_PHOTO("upload_photo", "上传头像"),

    COMPLETE_INFO("complete_info", "完善用户信息"),

    LOGIN("login", "登录"),

    INVITE_USER("invite_user", "邀请好友注册"),

    TOOL_BUY("tool_buy", "购买道具"),

    ADOPT_BUY_DEDUCT("adopt_buy_deduct", "认养抵扣"),

    ADOPT_PAY_BACK("adopt_pay_back", "认养消费返利");

    public static EJourBizTypeUser getBizType(String code) {
        Map<String, EJourBizTypeUser> map = getBizTypeMap();
        EJourBizTypeUser result = map.get(code);
        if (result == null) {
            throw new BizException("XN0000", code + "对应的jourBizType不存在");
        }
        return result;
    }

    public static Map<String, EJourBizTypeUser> getBizTypeMap() {
        Map<String, EJourBizTypeUser> map = new HashMap<String, EJourBizTypeUser>();
        for (EJourBizTypeUser bizType : EJourBizTypeUser.values()) {
            map.put(bizType.getCode(), bizType);
        }
        return map;
    }

    EJourBizTypeUser(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

}
