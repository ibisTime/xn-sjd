package com.ogc.standard.domain;

import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 集体认养订单
* @author: jiafr 
* @since: 2018-09-27 20:31:24
* @history:
*/
public class GroupAdoptOrder extends ABaseDO {

    private static final long serialVersionUID = -4213921746085242205L;

    // 编号
    private String code;

    // 识别码
    private String identifyCode;

    // 认养产品编号
    private String productCode;

    // 规格名称
    private String productSpecsName;

    // 认养价格
    private Long price;

    // 认养年限
    private float year;

    // 认养开始时间
    private Date startDatetime;

    // 认养结束时间
    private Date endDatetime;

    // 数量
    private String quantity;

    // 金额
    private Long amount;

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

    public void setIdentifyCode(String identifyCode) {
        this.identifyCode = identifyCode;
    }

    public String getIdentifyCode() {
        return identifyCode;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public float getYear() {
        return year;
    }

    public void setYear(float year) {
        this.year = year;
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
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
