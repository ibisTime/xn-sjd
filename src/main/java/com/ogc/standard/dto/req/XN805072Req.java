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
    private String bussinessLicense;

    // 企业地址
    @NotBlank
    private String companyAddress;

    // 企业法人姓名
    @NotBlank
    private String companyChargerName;

    // 企业法人联系方式
    @NotBlank
    private String companyChargerMobile;

    // 企业法人身份证
    @NotBlank
    private String companyChargerIdNo;

    // 注册统一编码
    @NotBlank
    private String bussinessLicenseId;

    // 企业开户行
    @NotBlank
    private String companyBank;

    // 企业开户行账号
    @NotBlank
    private String companyBankNumber;

    // 企业联系人
    @NotBlank
    private String companyContactName;

    // 企业联系人电话
    @NotBlank
    private String companyContactMobile;

    // 企业联系人地址
    @NotBlank
    private String companyContactAddress;

    // 企业法人身份证正面
    @NotBlank
    private String companyChargerIdPic;

    // 企业法人身份证反面
    @NotBlank
    private String companyChargerBackIdPic;

    // 企业简介
    private String companyIntroduce;

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

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyChargerName() {
        return companyChargerName;
    }

    public void setCompanyChargerName(String companyChargerName) {
        this.companyChargerName = companyChargerName;
    }

    public String getCompanyChargerMobile() {
        return companyChargerMobile;
    }

    public void setCompanyChargerMobile(String companyChargerMobile) {
        this.companyChargerMobile = companyChargerMobile;
    }

    public String getCompanyChargerIdNo() {
        return companyChargerIdNo;
    }

    public void setCompanyChargerIdNo(String companyChargerIdNo) {
        this.companyChargerIdNo = companyChargerIdNo;
    }

    public String getCompanyBank() {
        return companyBank;
    }

    public void setCompanyBank(String companyBank) {
        this.companyBank = companyBank;
    }

    public String getCompanyBankNumber() {
        return companyBankNumber;
    }

    public void setCompanyBankNumber(String companyBankNumber) {
        this.companyBankNumber = companyBankNumber;
    }

    public String getCompanyContactName() {
        return companyContactName;
    }

    public void setCompanyContactName(String companyContactName) {
        this.companyContactName = companyContactName;
    }

    public String getCompanyContactMobile() {
        return companyContactMobile;
    }

    public void setCompanyContactMobile(String companyContactMobile) {
        this.companyContactMobile = companyContactMobile;
    }

    public String getCompanyContactAddress() {
        return companyContactAddress;
    }

    public void setCompanyContactAddress(String companyContactAddress) {
        this.companyContactAddress = companyContactAddress;
    }

    public String getCompanyChargerIdPic() {
        return companyChargerIdPic;
    }

    public void setCompanyChargerIdPic(String companyChargerIdPic) {
        this.companyChargerIdPic = companyChargerIdPic;
    }

    public String getCompanyChargerBackIdPic() {
        return companyChargerBackIdPic;
    }

    public void setCompanyChargerBackIdPic(String companyChargerBackIdPic) {
        this.companyChargerBackIdPic = companyChargerBackIdPic;
    }

    public String getCompanyIntroduce() {
        return companyIntroduce;
    }

    public void setCompanyIntroduce(String companyIntroduce) {
        this.companyIntroduce = companyIntroduce;
    }

}
