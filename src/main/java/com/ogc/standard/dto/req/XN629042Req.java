package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 支付认养订单
 * @author: jiafr 
 * @since: 2018年9月27日 下午3:29:16 
 * @history:
 */
public class XN629042Req {

    // 编号
    @NotBlank
    private String code;

    // 支付方式
    @NotBlank
    private String payType;

    // 支付方式
    @NotBlank
    private String isJfDeduct;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getIsJfDeduct() {
        return isJfDeduct;
    }

    public void setIsJfDeduct(String isJfDeduct) {
        this.isJfDeduct = isJfDeduct;
    }

}
