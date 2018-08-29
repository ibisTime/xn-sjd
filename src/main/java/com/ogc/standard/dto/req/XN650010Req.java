package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 列表查询组合币种配置（详细仓位）（front）
 * @author: lei 
 * @since: 2018年8月21日 下午4:42:37 
 * @history:
 */
public class XN650010Req {

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
