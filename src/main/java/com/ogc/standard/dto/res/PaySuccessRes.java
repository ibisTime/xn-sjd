package com.ogc.standard.dto.res;

public class PaySuccessRes {

    // 是否成功
    private boolean isSuccess;

    // 业务类型
    private String bizType;

    // 业务编号
    private String bizCode;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public PaySuccessRes() {
    };

    public PaySuccessRes(boolean isSuccess, String bizType, String bizCode) {
        this.isSuccess = isSuccess;
        this.bizType = bizType;
        this.bizCode = bizCode;
    }
}
