package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN802200Req {

    @NotBlank
    String address;

    @NotBlank
    private String updater;

    private String remark;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
