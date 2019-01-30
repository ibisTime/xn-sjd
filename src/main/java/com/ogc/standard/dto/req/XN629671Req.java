package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 添加公章
 * @author: silver 
 * @since: Jan 25, 2019 4:12:55 PM 
 * @history:
 */
public class XN629671Req {

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
