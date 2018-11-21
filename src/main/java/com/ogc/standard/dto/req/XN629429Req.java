package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 当前订单积分抵扣金额
 * @author: silver 
 * @since: Nov 3, 2018 5:31:02 PM 
 * @history:
 */
public class XN629429Req {
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
