package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户是否点赞/收藏
 * @author: silver 
 * @since: Nov 23, 2018 5:11:07 PM 
 * @history:
 */
public class XN629348Req {
    // 编号
    @NotBlank
    private String code;

    // 用户编号
    @NotBlank
    private String userId;

    // 类型
    @NotBlank
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
