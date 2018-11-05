package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 确认收货
 * @author: silver 
 * @since: Nov 5, 2018 8:11:29 PM 
 * @history:
 */
public class XN629461Req {

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
