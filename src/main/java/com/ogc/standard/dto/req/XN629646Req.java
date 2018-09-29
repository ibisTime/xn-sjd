package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 分页查询结算订单
 * @author: silver 
 * @since: Sep 29, 2018 5:50:56 PM 
 * @history:
 */
public class XN629646Req {
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
