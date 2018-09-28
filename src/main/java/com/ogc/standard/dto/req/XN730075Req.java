package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改手机号
 * @author: silver 
 * @since: 2018年9月28日 下午3:49:20 
 * @history:
 */
public class XN730075Req extends BaseReq {

    private static final long serialVersionUID = -6308551102694787370L;

    // 用户编号
    @NotBlank
    private String userId;

    // 新手机号
    @NotBlank
    private String newMobile;

    // 验证码
    @NotBlank
    private String smsCaptcha;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNewMobile() {
        return newMobile;
    }

    public void setNewMobile(String newMobile) {
        this.newMobile = newMobile;
    }

    public String getSmsCaptcha() {
        return smsCaptcha;
    }

    public void setSmsCaptcha(String smsCaptcha) {
        this.smsCaptcha = smsCaptcha;
    }

}
