package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 审核评论(oss)
 * @author: silver 
 * @since: 2018年8月22日 上午11:02:12 
 * @history:
 */
public class XN628270Req {
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
    private String approveNote;

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

    public String getApproveNote() {
        return approveNote;
    }

    public void setApproveNote(String approveNote) {
        this.approveNote = approveNote;
    }

}
