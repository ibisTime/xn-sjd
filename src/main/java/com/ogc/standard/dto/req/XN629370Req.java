package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 分享
 * @author: silver 
 * @since: Sep 30, 2018 12:33:37 PM 
 * @history:
 */
public class XN629370Req extends BaseReq {

    private static final long serialVersionUID = -6308551102694787370L;

    // 分享人
    @NotBlank
    private String userId;

    // 分享渠道(0微信/1朋友圈)
    @NotBlank
    private String channel;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

}
