/**
 * @Title XN805086Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年9月12日 上午10:29:09 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 获取腾讯云签名
 * @author: taojian 
 * @since: 2018年9月12日 上午10:29:09 
 * @history:
 */
public class XN805087Req {

    // 用户id
    @NotBlank
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
