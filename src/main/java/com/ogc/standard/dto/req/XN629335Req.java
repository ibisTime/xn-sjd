package com.ogc.standard.dto.req;

/**
 * 分页查询赠送记录
 * @author: jiafr 
 * @since: 2018年9月28日 下午2:22:07 
 * @history:
 */
public class XN629335Req extends APageReq {

    private static final long serialVersionUID = -7883970226659861025L;

    // 认养权编号
    private String adoptTreeCode;

    // 赠送人
    private String userId;

    // 被赠送人
    private String toUserId;

    // 赠送时间
    private String createDatetime;

    public String getAdoptTreeCode() {
        return adoptTreeCode;
    }

    public void setAdoptTreeCode(String adoptTreeCode) {
        this.adoptTreeCode = adoptTreeCode;
    }

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

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

}
