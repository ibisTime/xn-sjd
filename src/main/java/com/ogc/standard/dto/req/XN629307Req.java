package com.ogc.standard.dto.req;

/**
 * 列表查询日志
 * @author: jiafr 
 * @since: 2018年9月28日 下午3:08:46 
 * @history:
 */
public class XN629307Req extends AListReq {

    private static final long serialVersionUID = -1957830952141924907L;

    // 认养权编号
    private String adoptTreeCode;

    // 类型（0赠送/1留言/2分享/3收取碳泡泡）
    private String type;

    // 操作人
    private String userId;

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

}
