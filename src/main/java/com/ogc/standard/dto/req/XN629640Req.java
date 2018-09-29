package com.ogc.standard.dto.req;

/**
 * 审核结算订单
 * @author: silver 
 * @since: Sep 29, 2018 5:41:10 PM 
 * @history:
 */
public class XN629640Req extends BaseReq {

    private static final long serialVersionUID = -6308551102694787370L;

    // 参考订单编号
    private String refCode;

    // 审核结果（0不通过/1通过）
    private String approveResult;

    // 更新人
    private String updater;

    // 处理说明
    private String handleNote;

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
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

    public String getHandleNote() {
        return handleNote;
    }

    public void setHandleNote(String handleNote) {
        this.handleNote = handleNote;
    }

}
