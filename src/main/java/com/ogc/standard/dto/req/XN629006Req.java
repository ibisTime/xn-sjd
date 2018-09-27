package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详细查询产品分类
 * @author: silver 
 * @since: 2018年9月26日 下午5:47:38 
 * @history:
 */
public class XN629006Req {

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
