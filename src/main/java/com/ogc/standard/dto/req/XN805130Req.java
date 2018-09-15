/**
 * @Title XN805130Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年9月13日 下午2:42:37 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 修改手机号申请
 * @author: taojian 
 * @since: 2018年9月13日 下午2:42:37 
 * @history:
 */
public class XN805130Req {
    // 验证码
    @NotBlank
    private String captcha;

    // 手机号
    @NotBlank
    private String mobile;

    // 手持证件照
    @NotBlank
    private String idHold;

    // 用户编号
    @NotBlank
    private String userId;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdHold() {
        return idHold;
    }

    public void setIdHold(String idHold) {
        this.idHold = idHold;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
