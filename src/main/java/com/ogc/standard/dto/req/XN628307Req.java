package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 我的报名分页查询
 * @author: silver 
 * @since: 2018年8月21日 下午3:07:50 
 * @history:
 */
public class XN628307Req extends APageReq {
    private static final long serialVersionUID = -1397348212917126608L;

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
