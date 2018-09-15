/**
 * @Title XN805150Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年9月14日 下午12:25:29 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 信任拉黑用户
 * @author: taojian 
 * @since: 2018年9月14日 下午12:25:29 
 * @history:
 */
public class XN805150Req {
    // 用户编号
    @NotBlank
    private String userId;

    // 对象用户编号
    @NotBlank
    private String toUser;

    // 类型 0拉黑 1信任
    @NotBlank
    private String type;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
