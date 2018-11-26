package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 支付预售订单
 * @author: silver 
 * @since: Nov 3, 2018 5:22:34 PM 
 * @history:
 */
public class XN629422Req {
    // 编号
    @NotBlank
    private String code;

    // 支付方式
    @NotBlank
    private String payType;

    // 支付密码
    private String tradePwd;

    // 积分抵扣
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

    public String getTradePwd() {
        return tradePwd;
    }

    public void setTradePwd(String tradePwd) {
        this.tradePwd = tradePwd;
    }

    public String getIsJfDeduct() {
        return isJfDeduct;
    }

    public void setIsJfDeduct(String isJfDeduct) {
        this.isJfDeduct = isJfDeduct;
    }

}
