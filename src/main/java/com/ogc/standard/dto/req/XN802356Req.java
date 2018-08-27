package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 取现详情查询
 * @author: xieyj 
 * @since: 2017年5月12日 上午10:03:42 
 * @history:
 */
public class XN802356Req {

    @NotBlank
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
