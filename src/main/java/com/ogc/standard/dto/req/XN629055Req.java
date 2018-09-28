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
