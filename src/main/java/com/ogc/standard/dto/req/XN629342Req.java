package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 审核文章
 * @author: jiafr 
 * @since: 2018年10月2日 上午1:55:19 
 * @history:
 */
public class XN629342Req {

    // 编号
    @NotBlank
    private String code;

    // 操作人
    @NotBlank
    private String updater;

    // 审核结果1通过0不通过
    @NotBlank
    private String approveResult;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

}
