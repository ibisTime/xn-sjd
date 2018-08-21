package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN805067Req {

    // userId（必填）
    @NotBlank
    private String userId;

    // 新支付密码（必填）
    @NotBlank
    private String newTradePwd;

    // 短信验证码（必填）
    @NotBlank
    private String smsCaptcha;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNewTradePwd() {
        return newTradePwd;
    }

    public void setNewTradePwd(String newTradePwd) {
        this.newTradePwd = newTradePwd;
    }

    public String getSmsCaptcha() {
        return smsCaptcha;
    }

    public void setSmsCaptcha(String smsCaptcha) {
        this.smsCaptcha = smsCaptcha;
    }

}
