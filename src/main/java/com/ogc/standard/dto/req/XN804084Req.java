package com.ogc.standard.dto.req;

/**
 * 发送短信验证码
 * @author: xieyj 
 * @since: 2017年2月13日 下午1:47:40 
 * @history:
 */
public class XN804084Req {

    // 国际代码--必填
    private String interCode;

    // 接受短信的手机号--必填
    private String mobile;

    // 业务类型--必填
    private String bizType;

    // 模板编号
    private String templateId;

    // 公司编号(选填)
    private String companyCode;

    // 系统编号(必填)
    private String systemCode;

    public String getInterCode() {
        return interCode;
    }

    public void setInterCode(String interCode) {
        this.interCode = interCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

}
