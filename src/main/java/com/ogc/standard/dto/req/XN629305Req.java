package com.ogc.standard.dto.req;

/**
 * 分页查询日志
 * @author: jiafr 
 * @since: 2018年9月28日 下午2:53:55 
 * @history:
 */
public class XN629305Req extends APageReq {

    private static final long serialVersionUID = 8511651091614218636L;

    // 认养权编号
    private String adoptTreeCode;

    // 类型（0赠送/1留言/2分享/3收取碳泡泡）
    private String type;

    // 操作人
    private String userId;

    // 认养人编号
    private String adoptUserId;

    // 产生时间起
    private String createDatetimeStart;

    // 产生时间止
    private String createDatetimeEnd;

    public String getAdoptTreeCode() {
        return adoptTreeCode;
    }

    public void setAdoptTreeCode(String adoptTreeCode) {
        this.adoptTreeCode = adoptTreeCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreateDatetimeStart() {
        return createDatetimeStart;
    }

    public void setCreateDatetimeStart(String createDatetimeStart) {
        this.createDatetimeStart = createDatetimeStart;
    }

    public String getCreateDatetimeEnd() {
        return createDatetimeEnd;
    }

    public void setCreateDatetimeEnd(String createDatetimeEnd) {
        this.createDatetimeEnd = createDatetimeEnd;
    }

    public String getAdoptUserId() {
        return adoptUserId;
    }

    public void setAdoptUserId(String adoptUserId) {
        this.adoptUserId = adoptUserId;
    }

}
