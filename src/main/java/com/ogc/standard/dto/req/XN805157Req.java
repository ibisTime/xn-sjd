package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN805157Req extends APageReq {

    private static final long serialVersionUID = 710797965742101051L;

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
