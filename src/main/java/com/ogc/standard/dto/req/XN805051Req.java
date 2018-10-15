package com.ogc.standard.dto.req;

/**
 * 微信登录
 * @author: silver 
 * @since: Oct 15, 2018 10:24:01 PM 
 * @history:
 */
public class XN805051Req {

    // 开放编号（必填）
    private String code;

    // 类型(必填 微信h5=3 微信APP=4)
    private String type;

    // 是否强制绑定手机号
    private String isNeedMobile;

    // 手机号（选填）
    private String mobile;

    // 类型(必填)
    private String kind;

    // 短信验证码（选填）
    private String smsCaptcha;

    // 推荐人（选填）
    private String userReferee;

    // 推荐人类型（选填）
    private String userRefereeKind;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsNeedMobile() {
        return isNeedMobile;
    }

    public void setIsNeedMobile(String isNeedMobile) {
        this.isNeedMobile = isNeedMobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getSmsCaptcha() {
        return smsCaptcha;
    }

    public void setSmsCaptcha(String smsCaptcha) {
        this.smsCaptcha = smsCaptcha;
    }

    public String getUserReferee() {
        return userReferee;
    }

    public void setUserReferee(String userReferee) {
        this.userReferee = userReferee;
    }

    public String getUserRefereeKind() {
        return userRefereeKind;
    }

    public void setUserRefereeKind(String userRefereeKind) {
        this.userRefereeKind = userRefereeKind;
    }
}
