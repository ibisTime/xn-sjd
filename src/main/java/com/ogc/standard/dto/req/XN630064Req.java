package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 代申请
 * @author: jiafr 
 * @since: 2018年9月28日 下午6:08:58 
 * @history:
 */
public class XN630064Req {

    // 类型 O产权,M养护
    @NotBlank
    private String kind;

    // 用户名
    @NotBlank
    private String loginName;

    // 手机号
    @NotBlank
    private String mobile;

    // 真实姓名
    @NotBlank
    private String realName;

    // 备注
    private String remark;

    // ******************* 公司 *******************

    // 营业执照
    @NotBlank
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

    // 组织机构代码
    private String organizationCode;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

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

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

}
