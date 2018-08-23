package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 点赞/取消(front)
 * @author: silver 
 * @since: 2018年8月23日 上午10:50:03 
 * @history:
 */
public class XN628036Req {
    // 编号
    @NotBlank
    private String code;

    // 用户编号人
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
