/**
 * @Title XN805088Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年9月12日 下午10:15:24 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 
 * @author: taojian 
 * @since: 2018年9月12日 下午10:15:24 
 * @history:
 */
public class XN805088Req {
    // 谷歌验证码
    @NotBlank
    private String googleCaptcha;

    // 短信验证码
    @NotBlank
    private String smsCaptcha;

    // 谷歌验证密钥
    @NotBlank
    private String secret;

    // 用户ID
    @NotBlank
    private String userId;

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

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
