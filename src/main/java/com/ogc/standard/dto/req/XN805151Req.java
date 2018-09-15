package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 解除关系
 * @author: taojian 
 * @since: 2018年9月13日 下午5:33:39 
 * @history:
 */
public class XN805151Req {
    // 用户编号
    @NotBlank
    private String userId;

    // 对象用户编号
    @NotBlank
    private String toUser;

    // 类型
    @NotBlank
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

}
