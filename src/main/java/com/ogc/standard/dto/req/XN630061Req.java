package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 审核用户（平台方）
 * @author: jiafr 
 * @since: 2018年9月28日 下午8:36:48 
 * @history:
 */
public class XN630061Req {

    // 用户id
    @NotBlank
    private String userId;

    // 更新人
    @NotBlank
    private String updater;

    // 审核结果
    @NotBlank
    private String approveResult;

    // 备注
    private String remark;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getApproveResult() {
        return approveResult;
    }

    public void setApproveResult(String approveResult) {
        this.approveResult = approveResult;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
