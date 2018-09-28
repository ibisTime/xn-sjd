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

    // 认养开始时间
    private String startDatetime;

    // 认养结束时间
    private String endDatetime;

    // 下单时间
    private String applyDatetime;

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

    public String getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(String startDatetime) {
        this.startDatetime = startDatetime;
    }

    public String getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(String endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(String applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

}
