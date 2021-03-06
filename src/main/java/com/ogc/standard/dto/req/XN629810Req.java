package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 点赞/取消点赞
 * @author: silver 
 * @since: Nov 23, 2018 1:14:32 PM 
 * @history:
 */
public class XN629810Req {

    // 编号
    @NotBlank
    private String code;

    // 操作人
    @NotBlank
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
