package com.ogc.standard.dto.req;

/**
 * 列表查询分享记录
 * @author: silver 
 * @since: Sep 30, 2018 11:22:10 AM 
 * @history:
 */
public class XN629377Req extends AListReq {

    private static final long serialVersionUID = 8511651091614218636L;

    // 分享人
    private String userId;

    // 分享渠道(0微信/1朋友圈)
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
