package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查询申请绑定养护方
 * @author: jiafr 
 * @since: 2018年9月27日 上午9:46:46 
 * @history:
 */
public class XN629606Req {

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
