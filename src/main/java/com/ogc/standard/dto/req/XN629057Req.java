package com.ogc.standard.dto.req;

/**
 * 列表查询申请绑定养护方
 * @author: jiafr 
 * @since: 2018年9月27日 上午11:23:45 
 * @history:
 */
public class XN629057Req extends AListReq {

    private static final long serialVersionUID = -2303074676623664238L;

    // 认养产品编号
    private String productCode;

    // 规格名称
    private String productSpecsName;

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
