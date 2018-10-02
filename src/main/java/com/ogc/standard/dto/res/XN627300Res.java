package com.ogc.standard.dto.res;

public class XN627300Res {
    private String userId;

    // 审核状态
    private String status;

    public XN627300Res() {
    }

    public XN627300Res(String userId) {
        this.userId = userId;
    }

    public XN627300Res(String userId, String status) {
        super();
        this.userId = userId;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
