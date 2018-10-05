package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 本周能量比拼
 * @author: silver 
 * @since: Oct 5, 2018 3:16:50 PM 
 * @history:
 */
public class XN629900Req extends BaseReq {

    private static final long serialVersionUID = 809941854249760290L;

    // 用户编号
    @NotBlank
    private String userId;

    // 好友用户编号
    @NotBlank
    private String toUserId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

}
