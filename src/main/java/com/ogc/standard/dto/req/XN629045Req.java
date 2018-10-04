package com.ogc.standard.dto.req;

import java.util.List;

/**
 * 分页查询个人捐赠专属认养订单
 * @author: jiafr 
 * @since: 2018年9月27日 下午5:04:37 
 * @history:
 */
public class XN629045Req extends APageReq {

    private static final long serialVersionUID = 6541979603349552583L;

    // 订单类型（1个人/2定向/3捐赠）
    private String type;

    // 认养产品编号
    private String productCode;

    // 规格名称
    private String productSpecsName;

    // 用户编号
    private String userId;

    // 状态(0待支付1已取消2待认养3认养中4已到期)
    private String status;

    private List<String> statusList;

    private String settleStatus;

    private String startDatetimeStart;// 认养开始时间起

    private String startDatetimeEnd;// 认养开始时间止

    private String endDatetimeStart;// 认养结束时间起

    private String endDatetimeEnd;// 认养结束时间止

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(String settleStatus) {
        this.settleStatus = settleStatus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductSpecsName() {
        return productSpecsName;
    }

    public void setProductSpecsName(String productSpecsName) {
        this.productSpecsName = productSpecsName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartDatetimeStart() {
        return startDatetimeStart;
    }

    public void setStartDatetimeStart(String startDatetimeStart) {
        this.startDatetimeStart = startDatetimeStart;
    }

    public String getStartDatetimeEnd() {
        return startDatetimeEnd;
    }

    public void setStartDatetimeEnd(String startDatetimeEnd) {
        this.startDatetimeEnd = startDatetimeEnd;
    }

    public String getEndDatetimeStart() {
        return endDatetimeStart;
    }

    public void setEndDatetimeStart(String endDatetimeStart) {
        this.endDatetimeStart = endDatetimeStart;
    }

    public String getEndDatetimeEnd() {
        return endDatetimeEnd;
    }

    public void setEndDatetimeEnd(String endDatetimeEnd) {
        this.endDatetimeEnd = endDatetimeEnd;
    }
}
