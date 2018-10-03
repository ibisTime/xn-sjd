package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN629208Req extends APageReq {

    private static final long serialVersionUID = 8479425657381483060L;

    // 用户编号
    @NotBlank
    private String userId;

    private String categoryCode;

    // 状态
    private String status;

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
