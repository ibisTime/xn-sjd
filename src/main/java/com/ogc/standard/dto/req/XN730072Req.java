package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 申请分销商
 * @author: silver 
 * @since: 2018年9月28日 下午3:49:20 
 * @history:
 */
public class XN730072Req extends BaseReq {

    private static final long serialVersionUID = -6308551102694787370L;

    // 真实姓名
    @NotBlank
    private String realName;

    // 头像
    private String photo;

    // 手机号
    @NotBlank
    private String mobile;

    // 登陆密码
    @NotBlank
    private String loginPwd;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

}
