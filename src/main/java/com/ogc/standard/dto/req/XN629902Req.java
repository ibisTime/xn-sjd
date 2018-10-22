package com.ogc.standard.dto.req;

/**
 * 代理商佣金
 * @author: silver 
 * @since: Oct 22, 2018 4:20:25 PM 
 * @history:
 */
public class XN629902Req extends BaseReq {

    private static final long serialVersionUID = 809941854249760290L;

    // 用户编号
    private String userId;

    // 状态 （0待结算/1已结算/2不结算）
    private String status;

    // 新增开始时间
    private String createStartDatetime;

    // 新增结束时间
    private String createEndDatetime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
