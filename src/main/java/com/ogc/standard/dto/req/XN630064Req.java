package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 填写证书
 * @author: xieyj 
 * @since: 2018年10月4日 下午1:45:59 
 * @history:
 */
public class XN630064Req {

    @NotBlank
    private String userId;

    // 证书模板
    @NotBlank
    private String certificateTemplate;

    // 合同模板
    @NotBlank
    private String contractTemplate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCertificateTemplate() {
        return certificateTemplate;
    }

    public void setCertificateTemplate(String certificateTemplate) {
        this.certificateTemplate = certificateTemplate;
    }

    public String getContractTemplate() {
        return contractTemplate;
    }

    public void setContractTemplate(String contractTemplate) {
        this.contractTemplate = contractTemplate;
    }
}
