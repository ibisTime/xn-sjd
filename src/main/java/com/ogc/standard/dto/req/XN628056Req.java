package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查询帖子(oss)
 * @author: silver 
 * @since: 2018年8月22日 下午4:26:59 
 * @history:
 */
public class XN628056Req {
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
