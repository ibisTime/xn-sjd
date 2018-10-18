package com.ogc.standard.dto.res;

import java.math.BigDecimal;

public class XN805051Res {
    // 用户编号
    private String userId;

    // 是否绑定手机号
    private String isNeedMobile;

    // 送积分
    private BigDecimal jfAmount;

    public BigDecimal getJfAmount() {
        return jfAmount;
    }

    public void setJfAmount(BigDecimal jfAmount) {
        this.jfAmount = jfAmount;
    }

    public XN805051Res() {
    }

    public XN805051Res(String userId) {
        this.userId = userId;
    }

    public XN805051Res(String userId, BigDecimal jfAmount) {
        this.userId = userId;
        this.jfAmount = jfAmount;
    }

    public XN805051Res(String userId, String isNeedMobile) {
        this.userId = userId;
        this.isNeedMobile = isNeedMobile;
    }

    public XN805051Res(String userId, String isNeedMobile,
            BigDecimal jfAmount) {
        super();
        this.userId = userId;
        this.isNeedMobile = isNeedMobile;
        this.jfAmount = jfAmount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIsNeedMobile() {
        return isNeedMobile;
    }

    public void setIsNeedMobile(String isNeedMobile) {
        this.isNeedMobile = isNeedMobile;
    }
}
