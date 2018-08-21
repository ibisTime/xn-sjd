package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查赛事
 * @author: silver 
 * @since: 2018年8月21日 下午12:17:50 
 * @history:
 */
public class XN628296Req {

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
