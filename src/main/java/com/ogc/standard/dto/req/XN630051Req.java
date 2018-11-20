package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN630051Req {
    // 登陆名（必填）
    @NotBlank
    private String loginName;

    // 登陆密码（必填）
    @NotBlank
    private String loginPwd;

    // 类型
    private String type;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
