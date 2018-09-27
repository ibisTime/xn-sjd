package com.ogc.standard.domain;

import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 个人/定向/捐赠认养订单
* @author: jiafr 
* @since: 2018-09-26 18:29:53
* @history:
*/
public class AdoptOrder extends ABaseDO {

    private static final long serialVersionUID = -7220968198245817450L;

    // 编号
    private String code;

    // 订单类型（1个人/2定向/3捐赠）
    private String type;

    // 认养产品编号
    private String productCode;

    // 规格名称
    private String productSpecsName;

    // 认养价格
    private String price;

    // 认养年限
    private String year;

    // 认养开始时间
    private String startDatetime;

    // 认养结束时间
    private String endDatetime;

    // 数量
    private String quantity;

    // 金额
    private String amount;

    // 下单人编号
    private String applyUser;

    // 下单时间
    private Date applyDatetime;

    // 状态
    private String status;

    // 支付方式
    private String payType;

    // 支付组号
    private String payGroup;

    // 支付渠道编号
    private String payCode;

    // 支付金额
    private String payAmount;

    // 积分抵扣金额
    private String jfDeductAmount;

    // 支付时间
    private String payDatetime;

    // 积分返点金额
    private String backJfAmount;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductSpecsName(String productSpecsName) {
        this.productSpecsName = productSpecsName;
    }

    public String getProductSpecsName() {
        return productSpecsName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setStartDatetime(String startDatetime) {
        this.startDatetime = startDatetime;
    }

    public String getStartDatetime() {
        return startDatetime;
    }

    public void setEndDatetime(String endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getEndDatetime() {
        return endDatetime;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public Date getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayGroup(String payGroup) {
        this.payGroup = payGroup;
    }

    public String getPayGroup() {
        return payGroup;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setJfDeductAmount(String jfDeductAmount) {
        this.jfDeductAmount = jfDeductAmount;
    }

    public String getJfDeductAmount() {
        return jfDeductAmount;
    }

    public void setPayDatetime(String payDatetime) {
        this.payDatetime = payDatetime;
    }

    public String getPayDatetime() {
        return payDatetime;
    }

    public void setBackJfAmount(String backJfAmount) {
        this.backJfAmount = backJfAmount;
    }

    public String getBackJfAmount() {
        return backJfAmount;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdater() {
        return updater;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

}
