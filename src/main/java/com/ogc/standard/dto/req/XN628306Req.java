package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详细查询参赛申请
 * @author: silver 
 * @since: 2018年8月21日 下午3:15:33 
 * @history:
 */
public class XN628306Req {
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
