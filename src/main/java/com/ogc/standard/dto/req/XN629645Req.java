package com.ogc.standard.dto.req;

/**
 * 分页查询结算订单
 * @author: silver 
 * @since: Sep 29, 2018 5:50:56 PM 
 * @history:
 */
public class XN629645Req extends APageReq {

    private static final long serialVersionUID = -6308551102694787370L;

    // 用户编号
    private String userId;

    // 用户种类
    private String userKind;

    // 参考类型
    private String refType;

    // 参考订单编号
    private String refCode;

    // 状态
    private String status;

    // 创建开始时间
    private String createStartDatetime;

    // 创建结束时间
    private String createEndDatetime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserKind() {
        return userKind;
    }

    public void setUserKind(String userKind) {
        this.userKind = userKind;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateStartDatetime() {
        return createStartDatetime;
    }

    public void setCreateStartDatetime(String createStartDatetime) {
        this.createStartDatetime = createStartDatetime;
    }

    public String getCreateEndDatetime() {
        return createEndDatetime;
    }

    public void setCreateEndDatetime(String createEndDatetime) {
        this.createEndDatetime = createEndDatetime;
    }

}
