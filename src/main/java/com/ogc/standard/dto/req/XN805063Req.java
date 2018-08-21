package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN805063Req {

    // 手机号(必填)
    @NotBlank
    private String mobile;

    // 手机验证码(必填)
    @NotBlank
    private String smsCaptcha;

    // 新登录密码(必填)
    @NotBlank
    private String newLoginPwd;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSmsCaptcha() {
        return smsCaptcha;
    }

    public void setSmsCaptcha(String smsCaptcha) {
        this.smsCaptcha = smsCaptcha;
    }

    public String getNewLoginPwd() {
        return newLoginPwd;
    }

    public void setNewLoginPwd(String newLoginPwd) {
        this.newLoginPwd = newLoginPwd;
    }

}
