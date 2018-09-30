package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查询来访人
 * @author: jiafr 
 * @since: 2018年9月30日 上午10:31:46 
 * @history:
 */
public class XN629316Req {

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
