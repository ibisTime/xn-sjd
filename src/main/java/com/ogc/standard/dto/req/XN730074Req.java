package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 审核分销商
 * @author: silver 
 * @since: 2018年9月28日 下午3:49:20 
 * @history:
 */
public class XN730074Req extends BaseReq {

    private static final long serialVersionUID = -6308551102694787370L;

    // 用户编号
    @NotBlank
    private String userId;

    // 审核结果
    @NotBlank
    private String approveResult;

    // 是否顶级
    private String isTop;

    // 上级用户编号
    private String parentUserId;

    // 更新人
    @NotBlank
    private String updater;

    // 备注
    private String remark;

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public String getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(String parentUserId) {
        this.parentUserId = parentUserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getApproveResult() {
        return approveResult;
    }

    public void setApproveResult(String approveResult) {
        this.approveResult = approveResult;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
