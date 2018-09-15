package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查认证记录
 * @author: taojian 
 * @since: 2018年9月13日 下午5:43:50 
 * @history:
 */
public class XN805166Req {
    // id
    @NotBlank
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
