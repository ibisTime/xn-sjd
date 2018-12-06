package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 认领二维码寄售
 * @author: silver 
 * @since: Nov 4, 2018 2:57:58 PM 
 * @history:
 */
public class XN629443Req {

    // 编号
    @NotBlank
    private String code;

    // 用户编号
    @NotBlank
    private String userId;

    // 数量
    private String quantity;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}
