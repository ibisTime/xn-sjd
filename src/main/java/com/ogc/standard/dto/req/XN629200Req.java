package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 赠送树
 * @author: jiafr 
 * @since: 2018年9月28日 下午1:36:42 
 * @history:
 */
public class XN629200Req {

    // 认养权编号
    @NotBlank
    private String code;

    // 赠送对象
    @NotBlank
    private String toUserId;

    // 赠送人
    @NotBlank
    private String userId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
