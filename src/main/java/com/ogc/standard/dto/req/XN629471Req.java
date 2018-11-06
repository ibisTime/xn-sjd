package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 支付订单
 * @author: silver 
 * @since: Nov 6, 2018 6:58:32 PM 
 * @history:
 */
public class XN629471Req {

    // 编号
    @NotBlank
    private String code;

    // 支付方式
    @NotBlank
    private String payType;

    // 交易密码
    private String tradePwd;

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

}
