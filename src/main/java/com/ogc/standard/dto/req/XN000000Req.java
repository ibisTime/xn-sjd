package com.ogc.standard.dto.req;

/**
 * 测试接口
 * @author: silver 
 * @since: Dec 17, 2018 2:19:44 PM 
 * @history:
 */
public class XN000000Req {
    String refNo;

    String bizType;

    String bizNote;

    String refundAmount;

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getBizNote() {
        return bizNote;
    }

    public void setBizNote(String bizNote) {
        this.bizNote = bizNote;
    }

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

}
