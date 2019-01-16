package com.ogc.standard.dto.req;

/**
 * 月份签到天数统计
 * @author: silver 
 * @since: Jan 16, 2019 11:35:05 AM 
 * @history:
 */
public class XN629906Req extends BaseReq {

    private static final long serialVersionUID = 809941854249760290L;

    // 用户编号
    private String userId;

    // 创建开始时间
    private String createStartDatetime;

    // 创建结束时间
    private String createEndDatetime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreateStartDatetime() {
        return createStartDatetime;
    }

    public void setCreateStartDatetime(String createStartDatetime) {
        this.createStartDatetime = createStartDatetime;
    }

    public String getCreateEndDatetime() {
        return createEndDatetime;
    }

    public void setCreateEndDatetime(String createEndDatetime) {
        this.createEndDatetime = createEndDatetime;
    }

}
