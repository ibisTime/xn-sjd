package com.ogc.standard.domain;

import java.util.Date;
import java.util.List;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 战队成员申请表
* @author: Silver
* @since: 2018年08月21日 下午06:06:21
* @history:
*/
public class TeamMemberApply extends ABaseDO {
    private static final long serialVersionUID = -8897194155565771041L;

    // 编号
    private String code;

    // 战队编号
    private String teamCode;

    // 申请人
    private String applyUser;

    // 申请时间
    private Date applyDatetime;

    // 状态（1待审核，2审核通过，3审核不通过）
    private String status;

    // 审核人
    private String approver;

    // 审核时间
    private Date approveDatetime;

    // 备注
    private String remark;

    /************DB Properties***************/

    // 申请开始时间
    private Date applyStartDatetime;

    // 申请结束时间
    private Date applyEndDatetime;

    // 审核开始时间
    private Date approveStartDatetime;

    // 审核结束时间
    private Date approveEndDatetime;

    // 状态列表
    private List<String> statusList;

    // 战队名称
    private String teamName;

    // 申请人
    private User applyUserInfo;

    // 项目状态列表
    private List<String> teamStatusList;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    public Date getApplyDatetime() {
        return applyDatetime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getApprover() {
        return approver;
    }

    public void setApproveDatetime(Date approveDatetime) {
        this.approveDatetime = approveDatetime;
    }

    public Date getApproveDatetime() {
        return approveDatetime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public Date getApplyStartDatetime() {
        return applyStartDatetime;
    }

    public void setApplyStartDatetime(Date applyStartDatetime) {
        this.applyStartDatetime = applyStartDatetime;
    }

    public Date getApplyEndDatetime() {
        return applyEndDatetime;
    }

    public void setApplyEndDatetime(Date applyEndDatetime) {
        this.applyEndDatetime = applyEndDatetime;
    }

    public Date getApproveStartDatetime() {
        return approveStartDatetime;
    }

    public void setApproveStartDatetime(Date approveStartDatetime) {
        this.approveStartDatetime = approveStartDatetime;
    }

    public Date getApproveEndDatetime() {
        return approveEndDatetime;
    }

    public void setApproveEndDatetime(Date approveEndDatetime) {
        this.approveEndDatetime = approveEndDatetime;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public User getApplyUserInfo() {
        return applyUserInfo;
    }

    public void setApplyUserInfo(User applyUserInfo) {
        this.applyUserInfo = applyUserInfo;
    }

    public List<String> getTeamStatusList() {
        return teamStatusList;
    }

    public void setTeamStatusList(List<String> teamStatusList) {
        this.teamStatusList = teamStatusList;
    }

}
