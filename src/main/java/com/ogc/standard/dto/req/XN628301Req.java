package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 参赛审核
 * @author: silver 
 * @since: 2018年8月21日 下午2:47:38 
 * @history:
 */
public class XN628301Req {
    // 编号
    @NotBlank
    private String code;

    // 审核结果（0=不通过/1=通过）
    @NotBlank
    private String approveResult;

    // 审核人
    @NotBlank
    private String approver;

    // 备注
    @NotBlank
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getApproveResult() {
        return approveResult;
    }

    public void setApproveResult(String approveResult) {
        this.approveResult = approveResult;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
