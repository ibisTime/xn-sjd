package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 根据支付组号查订单明细
 * @author: silver 
 * @since: Dec 5, 2018 3:01:46 PM 
 * @history:
 */
public class XN629802Req {
    // 编号
    @NotBlank
    private String payGroupCode;

    public String getPayGroupCode() {
        return payGroupCode;
    }

    public void setPayGroupCode(String payGroupCode) {
        this.payGroupCode = payGroupCode;
    }

}
