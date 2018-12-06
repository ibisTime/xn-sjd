package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 审核关系
 * @author: silver 
 * @since: Dec 6, 2018 4:33:43 PM 
 * @history:
 */
public class XN805152Req {
    @NotBlank
    private String code;

    // 用户编号
    @NotBlank
    private String userId;

    @NotBlank
    private String approveResult;

    @NotBlank
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
