package com.ogc.standard.dto.req;

/**
 * 列表查赛事
 * @author: silver 
 * @since: 2018年8月21日 下午12:19:32 
 * @history:
 */
public class XN628297Req extends AListReq {
    private static final long serialVersionUID = -6467711175909172186L;

    // 开始时间
    private String startDatetime;

    // 结束时间
    private String endDatetime;

    // 状态（1待发布，2已发布，3已开始，4已过期）
    private String status;

    public String getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(String startDatetime) {
        this.startDatetime = startDatetime;
    }

    public String getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(String endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
