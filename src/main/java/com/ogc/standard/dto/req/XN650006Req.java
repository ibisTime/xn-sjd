package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查询组合（oss）
 * @author: lei 
 * @since: 2018年8月20日 下午9:25:51 
 * @history:
 */
public class XN650006Req {

    // 必填，组合编号
    @NotBlank
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
