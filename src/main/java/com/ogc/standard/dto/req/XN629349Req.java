package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 用户收藏的文章
 * @author: silver 
 * @since: Nov 23, 2018 5:11:07 PM 
 * @history:
 */
public class XN629349Req {

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
