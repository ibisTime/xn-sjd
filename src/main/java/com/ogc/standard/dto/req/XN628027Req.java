package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 我的报名申请分页查询(front)
 * @author: silver 
 * @since: 2018年8月21日 下午12:13:11 
 * @history:
 */
public class XN628027Req extends APageReq {

    private static final long serialVersionUID = 526930912942227980L;

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
