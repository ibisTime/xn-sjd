package com.ogc.standard.dto.req;

public class XN804082Req {
    // 系统编号(必填)
    private String systemCode;

    // 手机号(必填)
    private String mobile;

    // 验证码(必填)
    private String captcha;

    // 业务类型(必填)
    private String bizType;

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }
}
