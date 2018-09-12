/**
 * @Title XN630093Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年9月12日 上午10:31:38 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 发送邮箱验证码
 * @author: taojian 
 * @since: 2018年9月12日 上午10:31:38 
 * @history:
 */
public class XN630093Req {
    @NotBlank
    private String bizType;

    @NotBlank
    private String email;

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
