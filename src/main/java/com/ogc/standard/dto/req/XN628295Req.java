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

    // 开始时间
    private String startDatetime;

    // 结束时间
    private String endDatetime;

    // 状态（1待发布，2已发布，3已开始，4已过期）
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
