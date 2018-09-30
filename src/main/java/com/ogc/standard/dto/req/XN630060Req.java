package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 注册用户
 * @author: jiafr 
 * @since: 2018年9月28日 下午7:54:40 
 * @history:
 */
public class XN630060Req {

    // 类型
    @NotBlank
    private String kind;

    // 营业执照
    private String bussinessLicense;

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

    // 描述
    private String description;

    // 登录密码
    @NotBlank
    private String loginPwd;

    // 手机号
    @NotBlank
    private String mobile;

    // 组织机构代码
    private String organizationCode;

    public String getBussinessLicense() {
        return bussinessLicense;
    }

    public void setBussinessLicense(String bussinessLicense) {
        this.bussinessLicense = bussinessLicense;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

}
