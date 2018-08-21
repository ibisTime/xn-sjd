package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查询战队(oss)
 * @author: silver 
 * @since: 2018年8月21日 下午5:31:17 
 * @history:
 */
public class XN628016Req {
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
