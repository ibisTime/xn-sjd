package com.ogc.standard.dto.req;

/**
 * 分页查参赛申请
 * @author: silver 
 * @since: 2018年8月21日 下午3:07:50 
 * @history:
 */
public class XN628305Req extends APageReq {
    private static final long serialVersionUID = -1397348212917126608L;

    // 申请人编号
    private String applyUser;

    // 审核人
    private String approver;

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

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
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
