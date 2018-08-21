/**
 * @Title XN805145.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author dl  
 * @date 2018年8月20日 下午5:52:34 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: dl 
 * @since: 2018年8月20日 下午5:52:34 
 * @history:
 */
public class XN805145Req extends APageReq {

    private static final long serialVersionUID = -213328536116448889L;

    // 用户编号
    @NotBlank
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
