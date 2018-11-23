package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 发送弹幕
 * @author: silver 
 * @since: Nov 23, 2018 3:16:05 PM 
 * @history:
 */
public class XN629384Req {

    // 认养权编号
    @NotBlank
    private String adoptTreeCode;

    // 编号
    @NotBlank
    private String code;

    // 操作人
    @NotBlank
    private String userId;

    public String getAdoptTreeCode() {
        return adoptTreeCode;
    }

    public void setAdoptTreeCode(String adoptTreeCode) {
        this.adoptTreeCode = adoptTreeCode;
    }

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

}
