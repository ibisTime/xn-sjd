package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 微信登录(强制绑定手机号)
 * @author: silver 
 * @since: Oct 15, 2018 10:24:01 PM 
 * @history:
 */
public class XN805051Req {

    // 开放编号（必填）
    @NotBlank
    private String code;

    // 类型(必填 微信h5=wx_h5)
    @NotBlank
    private String type;

    // 类型(必填)
    @NotBlank
    private String kind;

    // 手机号（选填）
    private String mobile;

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
