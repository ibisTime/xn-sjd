package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 角色-详情
 * @author: Gejin 
 * @since: 2016年4月16日 下午5:24:01 
 * @history:
 */
public class XN630006Req {

    // 角色编号(必填)
    @NotBlank
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
