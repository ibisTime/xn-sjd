package com.ogc.standard.dto.req;

/**
 * 分页查赛事
 * @author: silver 
 * @since: 2018年8月21日 下午12:13:11 
 * @history:
 */
public class XN628295Req extends APageReq {

    private static final long serialVersionUID = 526930912942227980L;

    // 名称
    private String name;

    // 赛事开始开始时间
    private String startDatetimeStart;

    // 赛事开始结束时间
    private String startDatetimeEnd;

    // 赛事结束开始时间
    private String endDatetimeStart;

    // 赛事结束结束时间
    private String endDatetimeEnd;

    // 状态（1待发布，2已发布，3已开始，4已过期）
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDatetimeStart() {
        return startDatetimeStart;
    }

    public void setStartDatetimeStart(String startDatetimeStart) {
        this.startDatetimeStart = startDatetimeStart;
    }

    public String getStartDatetimeEnd() {
        return startDatetimeEnd;
    }

    public void setStartDatetimeEnd(String startDatetimeEnd) {
        this.startDatetimeEnd = startDatetimeEnd;
    }

    public String getEndDatetimeStart() {
        return endDatetimeStart;
    }

    public void setEndDatetimeStart(String endDatetimeStart) {
        this.endDatetimeStart = endDatetimeStart;
    }

    public String getEndDatetimeEnd() {
        return endDatetimeEnd;
    }

    public void setEndDatetimeEnd(String endDatetimeEnd) {
        this.endDatetimeEnd = endDatetimeEnd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
