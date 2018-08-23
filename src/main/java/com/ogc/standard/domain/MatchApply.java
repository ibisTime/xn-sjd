package com.ogc.standard.domain;

import java.util.Date;
import java.util.List;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 参赛申请表
* @author: Silver
* @since: 2018年08月21日 下午02:15:35
* @history:
*/
public class MatchApply extends ABaseDO {
    private static final long serialVersionUID = -3780182772018308885L;

    // 编号
    private String code;

    // 赛事编号
    private String matchCode;

    // 战队名称
    private String teamName;

    // logo
    private String logo;

    // 描述
    private String description;

    // 状态（1待审核，2审核通过，3审核不通过）
    private String status;

    // 申请人编号
    private String applyUser;

    // 申请时间
    private Date applyDatetime;

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

    // 赛事名称
    private String matchName;

    // 状态列表
    private List<String> statusList;

    // 申请人
    private String applyUserName;

    // 审核人
    private String approverName;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setMatchCode(String matchCode) {
        this.matchCode = matchCode;
    }

    public String getMatchCode() {
        return matchCode;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLogo() {
        return logo;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
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

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

}
