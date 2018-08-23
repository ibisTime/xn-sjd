package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查询帖子(front)
 * @author: silver 
 * @since: 2018年8月22日 下午4:26:59 
 * @history:
 */
public class XN628060Req {
    // 编号
    @NotBlank
    private String code;

    // 用户编号
    private String userId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
