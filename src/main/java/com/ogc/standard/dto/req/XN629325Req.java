package com.ogc.standard.dto.req;

/**
 * 分页查询礼物订单
 * @author: jiafr 
 * @since: 2018年9月30日 下午1:57:28 
 * @history:
 */
public class XN629325Req extends APageReq {

    private static final long serialVersionUID = 7156800597836297703L;

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

    // 被赠送人
    private String toUser;

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

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

}
