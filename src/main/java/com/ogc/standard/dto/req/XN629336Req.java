package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查询赠送记录
 * @author: jiafr 
 * @since: 2018年9月28日 下午2:29:30 
 * @history:
 */
public class XN629336Req {

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
