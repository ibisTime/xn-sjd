package com.ogc.standard.dto.req;

/**
 * 报名申请分页查询(front/oss)
 * @author: silver 
 * @since: 2018年8月21日 下午12:13:11 
 * @history:
 */
public class XN628025Req extends APageReq {

    private static final long serialVersionUID = 526930912942227980L;

    // 战队编号
    private String teamCode;

    // 申请人编号
    private String applyUser;

    // 状态（1待审核，2审核通过，3审核不通过）
    private String status;

    // 申请开始时间
    private String applyStartDatetime;

    // 申请结束时间
    private String applyEndDatetime;

    // 审核开始时间
    private String approveStartDatetime;

    // 审核结束时间
    private String approveEndDatetime;

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplyStartDatetime() {
        return applyStartDatetime;
    }

    public void setApplyStartDatetime(String applyStartDatetime) {
        this.applyStartDatetime = applyStartDatetime;
    }

    public String getApplyEndDatetime() {
        return applyEndDatetime;
    }

    public void setApplyEndDatetime(String applyEndDatetime) {
        this.applyEndDatetime = applyEndDatetime;
    }

    public String getApproveStartDatetime() {
        return approveStartDatetime;
    }

    public void setApproveStartDatetime(String approveStartDatetime) {
        this.approveStartDatetime = approveStartDatetime;
    }

    public String getApproveEndDatetime() {
        return approveEndDatetime;
    }

    public void setApproveEndDatetime(String approveEndDatetime) {
        this.approveEndDatetime = approveEndDatetime;
    }
}
