package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查询文章
 * @author: jiafr 
 * @since: 2018年10月2日 上午2:46:02 
 * @history:
 */
public class XN629346Req {
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
