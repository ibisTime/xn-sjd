package com.ogc.standard.dto.req;

/**
 * 列表查询赠送碳泡泡记录
 * @author: silver 
 * @since: Sep 30, 2018 11:22:10 AM 
 * @history:
 */
public class XN629367Req extends AListReq {

    private static final long serialVersionUID = 8511651091614218636L;

    // 赠送人
    private String userId;

    // 被赠送人
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
