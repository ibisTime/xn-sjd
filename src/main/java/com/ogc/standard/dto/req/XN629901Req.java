package com.ogc.standard.dto.req;

/**
 * 新增用户统计
 * @author: silver 
 * @since: Oct 22, 2018 11:17:57 AM 
 * @history:
 */
public class XN629901Req extends BaseReq {

    private static final long serialVersionUID = 809941854249760290L;

    // 用户编号
    private String userId;

    // 类型 (C:C端用户/O:产权方/M:养护方/A:代理商/P:平台方)，不传则查询全部类型
    private String type;

    // 新增开始时间
    private String createDatetimeStart;

    // 新增结束时间
    private String createDatetimeEnd;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
