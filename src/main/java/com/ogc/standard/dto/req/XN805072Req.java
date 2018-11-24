package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 企业认证
 * @author: silver 
 * @since: Nov 24, 2018 1:07:44 PM 
 * @history:
 */
public class XN805072Req {

    // userId（必填）
    @NotBlank
    private String userId;

    @NotBlank
    private String companyName;

    @NotBlank
    private String companyIntroduce;

    @NotBlank
    private String bussinessLicense;

    @NotBlank
    private String bussinessLicenseId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyIntroduce() {
        return companyIntroduce;
    }

    public void setCompanyIntroduce(String companyIntroduce) {
        this.companyIntroduce = companyIntroduce;
    }

    public String getBussinessLicense() {
        return bussinessLicense;
    }

    public void setBussinessLicense(String bussinessLicense) {
        this.bussinessLicense = bussinessLicense;
    }

    public String getBussinessLicenseId() {
        return bussinessLicenseId;
    }

    public void setBussinessLicenseId(String bussinessLicenseId) {
        this.bussinessLicenseId = bussinessLicenseId;
    }

}
