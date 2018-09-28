package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查询认养权
 * @author: jiafr 
 * @since: 2018年9月28日 下午2:03:39 
 * @history:
 */
public class XN629206Req {

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
