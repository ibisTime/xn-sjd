package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 查询抵扣金额
 * @author: xieyj 
 * @since: 2018年10月2日 下午4:41:20 
 * @history:
 */
public class XN629058Req {

    @NotBlank
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
