package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @author: lei 
 * @since: 2018年8月22日 下午3:23:53 
 * @history:
 */
public class XN650051Req {

    // 必填，委托单编号
    @NotBlank
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
