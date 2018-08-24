package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 我的帖子分页查(front)
 * @author: silver 
 * @since: 2018年8月22日 下午4:26:39 
 * @history:
 */
public class XN628058Req extends APageReq {
    private static final long serialVersionUID = -9019475126535672623L;

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
