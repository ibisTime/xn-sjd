package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详细查询资产
 * @author: silver 
 * @since: Nov 4, 2018 3:28:56 PM 
 * @history:
 */
public class XN629436Req {
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
