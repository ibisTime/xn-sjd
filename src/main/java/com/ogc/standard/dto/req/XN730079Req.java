package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 自助修改登录密码
 * @author: silver 
 * @since: 2018年9月28日 下午3:49:20 
 * @history:
 */
public class XN730079Req extends BaseReq {

    private static final long serialVersionUID = -6308551102694787370L;

    // 手机号
    @NotBlank
    private String mobile;

    // 密码
    @NotBlank
    private String newLoginPwd;

    // 验证码
    @NotBlank
    private String smsCaptcha;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNewLoginPwd() {
        return newLoginPwd;
    }

    public void setNewLoginPwd(String newLoginPwd) {
        this.newLoginPwd = newLoginPwd;
    }

    public String getSmsCaptcha() {
        return smsCaptcha;
    }

    public void setSmsCaptcha(String smsCaptcha) {
        this.smsCaptcha = smsCaptcha;
    }

}
