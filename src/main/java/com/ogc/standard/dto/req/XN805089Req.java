/**
 * @Title XN805089Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年9月12日 下午10:43:15 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: taojian 
 * @since: 2018年9月12日 下午10:43:15 
 * @history:
 */
public class XN805089Req {

    // 用户编号
    @NotBlank
    private String userId;

    // 短信验证码
    @NotBlank
    private String smsCaptcha;

    // 谷歌验证码
    @NotBlank
    private String googleCaptcha;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGoogleCaptcha() {
        return googleCaptcha;
    }

    public void setGoogleCaptcha(String googleCaptcha) {
        this.googleCaptcha = googleCaptcha;
    }

    public String getSmsCaptcha() {
        return smsCaptcha;
    }

    public void setSmsCaptcha(String smsCaptcha) {
        this.smsCaptcha = smsCaptcha;
    }

}
