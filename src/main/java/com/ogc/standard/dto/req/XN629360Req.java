package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 赠送碳泡泡
 * @author: silver 
 * @since: Sep 30, 2018 11:08:38 AM 
 * @history:
 */
public class XN629360Req extends BaseReq {

    private static final long serialVersionUID = -6308551102694787370L;

    // 赠送人
    @NotBlank
    private String userId;

    // 被赠送人
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
