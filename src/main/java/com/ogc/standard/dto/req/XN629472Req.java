package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 支付转增订单
 * @author: silver 
 * @since: Nov 12, 2018 7:31:52 PM 
 * @history:
 */
public class XN629472Req {

    // 编号
    @NotBlank
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
