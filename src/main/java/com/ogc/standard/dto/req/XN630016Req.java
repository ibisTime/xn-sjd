package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN630016Req {
    // 编号(必填)
    @NotBlank
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
