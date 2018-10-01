package com.ogc.standard.dto.req;

/**
 * 列表查询礼物订单
 * @author: jiafr 
 * @since: 2018年10月1日 上午9:41:35 
 * @history:
 */
public class XN629327Req extends AListReq {

    private static final long serialVersionUID = -9057423913075614130L;

    // 认养权编号
    private String adoptTreeCode;

    // 状态（0待认领/1已认领）
    private String status;

    // 认领人
    private String claimer;

    // 认领时间起
    private String claimDatetimeStart;

    // 认领时间止
    private String claimDatetimeEnd;

    public String getAdoptTreeCode() {
        return adoptTreeCode;
    }

    public void setAdoptTreeCode(String adoptTreeCode) {
        this.adoptTreeCode = adoptTreeCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClaimer() {
        return claimer;
    }

    public void setClaimer(String claimer) {
        this.claimer = claimer;
    }

    public String getClaimDatetimeStart() {
        return claimDatetimeStart;
    }

    public void setClaimDatetimeStart(String claimDatetimeStart) {
        this.claimDatetimeStart = claimDatetimeStart;
    }

    public String getClaimDatetimeEnd() {
        return claimDatetimeEnd;
    }

    public void setClaimDatetimeEnd(String claimDatetimeEnd) {
        this.claimDatetimeEnd = claimDatetimeEnd;
    }

}
