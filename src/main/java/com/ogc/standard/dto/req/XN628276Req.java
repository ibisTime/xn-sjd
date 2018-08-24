package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查询评论(oss)
 * @author: silver 
 * @since: 2018年8月21日 下午12:13:11 
 * @history:
 */
public class XN628276Req {
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
