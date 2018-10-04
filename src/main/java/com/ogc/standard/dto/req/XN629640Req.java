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

    // 参考订单类型
    private String refType;

    // 审核结果（0不通过/1通过）
    private String approveResult;

    // 处理人
    private String handler;

    // 处理说明
    private String handleNote;

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public String getApproveResult() {
        return approveResult;
    }

    public void setApproveResult(String approveResult) {
        this.approveResult = approveResult;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getHandleNote() {
        return handleNote;
    }

    public void setHandleNote(String handleNote) {
        this.handleNote = handleNote;
    }

}
