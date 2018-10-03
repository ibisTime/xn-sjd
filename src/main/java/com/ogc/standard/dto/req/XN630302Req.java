package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改公司
 * @author: jiafr 
 * @since: 2018年9月29日 上午10:18:03 
 * @history:
 */
public class XN630302Req {

    // 营业执照
    private String bussinessLicense;

    // 组织编号
    private String organizationCode;

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

    // 描述
    @NotBlank
    private String description;

    // 用户编号
    @NotBlank
    private String userId;

    public String getBussinessLicense() {
        return bussinessLicense;
    }

    public void setBussinessLicense(String bussinessLicense) {
        this.bussinessLicense = bussinessLicense;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getOrganizationCode() {
        return organizationCode;
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
}
