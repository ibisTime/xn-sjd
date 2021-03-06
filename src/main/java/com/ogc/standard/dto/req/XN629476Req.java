package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详细查询寄售订单
 * @author: silver 
 * @since: Nov 3, 2018 5:31:02 PM 
 * @history:
 */
public class XN629476Req {

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
