package com.ogc.standard.dto.req;

/**
 * 分页查询集体认养订单
 * @author: jiafr 
 * @since: 2018年9月27日 下午10:51:36 
 * @history:
 */
public class XN629055Req extends APageReq {

    private static final long serialVersionUID = -585586450144233268L;

    // 认养产品编号
    private String productCode;

    // 规格编号
    private String productSpecsCode;

    // 状态
    private String status;

    // 认养开始时间起
    private String startDatetimeStart;

    // 认养开始时间止
    private String startDatetimeEnd;

    // 认养结束时间起
    private String endDatetimeStart;

    // 认养结束时间止
    private String endDatetimeEnd;

    // 结算状态
    private String settleStatus;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductSpecsCode() {
        return productSpecsCode;
    }

    public void setProductSpecsCode(String productSpecsCode) {
        this.productSpecsCode = productSpecsCode;
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

    public String getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(String settleStatus) {
        this.settleStatus = settleStatus;
    }

}
