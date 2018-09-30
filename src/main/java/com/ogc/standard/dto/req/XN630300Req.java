package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 填写公司资料
 * @author: jiafr 
 * @since: 2018年9月30日 下午1:26:19 
 * @history:
 */
public class XN630300Req {

    // 营业执照
    private String bussinessLicense;

    // 证书模板
    private String certificateTemplate;

    // 负责人联系方式
    @NotBlank
    private String chargerMobile;

    // 公司地址
    @NotBlank
    private String companyAddress;

    // 公司负责人
    @NotBlank
    private String companyCharger;

    // 公司名称
    @NotBlank
    private String companyName;

    // 合同模板
    private String contractTemplate;

    // 简介
    private String description;

    // 用户id
    @NotBlank
    private String userId;

    // 组织机构代码
    private String organizationCode;

    public String getBussinessLicense() {
        return bussinessLicense;
    }

    public void setBussinessLicense(String bussinessLicense) {
        this.bussinessLicense = bussinessLicense;
    }

    public String getCertificateTemplate() {
        return certificateTemplate;
    }

    public void setCertificateTemplate(String certificateTemplate) {
        this.certificateTemplate = certificateTemplate;
    }

    public String getChargerMobile() {
        return chargerMobile;
    }

    public void setChargerMobile(String chargerMobile) {
        this.chargerMobile = chargerMobile;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyCharger() {
        return companyCharger;
    }

    public void setCompanyCharger(String companyCharger) {
        this.companyCharger = companyCharger;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContractTemplate() {
        return contractTemplate;
    }

    public void setContractTemplate(String contractTemplate) {
        this.contractTemplate = contractTemplate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

}
