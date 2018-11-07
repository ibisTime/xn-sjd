package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 列表查询寄售
 * @author: silver 
 * @since: Nov 4, 2018 3:28:56 PM 
 * @history:
 */
public class XN629458Req {
    // 用户编号
    @NotBlank

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
