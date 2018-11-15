package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 寄售订单
* @author: silver 
* @since: 2018-11-06 14:53:43
* @history:
*/
public class GroupOrder extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 组编号
    private String groupCode;

    // 资产归属人
    private String ownerId;

    // 产品编号
    private String productCode;

    // 产品名称
    private String productName;

    // 规格编号
    private String specsCode;

    // 规格名称
    private String specsName;

    // 价格
    private BigDecimal price;

    // 数量
    private Integer quantity;

    // 金额
    private BigDecimal amount;

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

    // 抵扣人民币
    private BigDecimal cnyDeductAmount;

    // 积分抵扣金额
    private BigDecimal jfDeductAmount;

    // 积分返点金额
    private BigDecimal backJfAmount;

    // 支付金额
    private BigDecimal payAmount;

    // 支付时间
    private Date payDatetime;

    // 结算状态
    private String settleStatus;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    /***************DB Properties***************/

    private Date applyDatetimeStart;// 下单时间起

    private Date applyDatetimeEnd;// 下单时间止

    // 预售产品
    private PresellProduct presellProduct;

    // 卖家
    private String sellerName;

    // 流水编号
    private String jourCode;

    // 树木编号
    private String treeNumbers;

    // 下单人名称
    private String applyUserName;

    // 转让类型
    private String presellType;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public Date getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayGroup() {
        return payGroup;
    }

    public void setPayGroup(String payGroup) {
        this.payGroup = payGroup;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public BigDecimal getCnyDeductAmount() {
        return cnyDeductAmount;
    }

    public void setCnyDeductAmount(BigDecimal cnyDeductAmount) {
        this.cnyDeductAmount = cnyDeductAmount;
    }

    public BigDecimal getJfDeductAmount() {
        return jfDeductAmount;
    }

    public void setJfDeductAmount(BigDecimal jfDeductAmount) {
        this.jfDeductAmount = jfDeductAmount;
    }

    public BigDecimal getBackJfAmount() {
        return backJfAmount;
    }

    public void setBackJfAmount(BigDecimal backJfAmount) {
        this.backJfAmount = backJfAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public Date getPayDatetime() {
        return payDatetime;
    }

    public void setPayDatetime(Date payDatetime) {
        this.payDatetime = payDatetime;
    }

    public String getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(String settleStatus) {
        this.settleStatus = settleStatus;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getApplyDatetimeStart() {
        return applyDatetimeStart;
    }

    public void setApplyDatetimeStart(Date applyDatetimeStart) {
        this.applyDatetimeStart = applyDatetimeStart;
    }

    public Date getApplyDatetimeEnd() {
        return applyDatetimeEnd;
    }

    public void setApplyDatetimeEnd(Date applyDatetimeEnd) {
        this.applyDatetimeEnd = applyDatetimeEnd;
    }

    public PresellProduct getPresellProduct() {
        return presellProduct;
    }

    public void setPresellProduct(PresellProduct presellProduct) {
        this.presellProduct = presellProduct;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getJourCode() {
        return jourCode;
    }

    public void setJourCode(String jourCode) {
        this.jourCode = jourCode;
    }

    public String getTreeNumbers() {
        return treeNumbers;
    }

    public void setTreeNumbers(String treeNumbers) {
        this.treeNumbers = treeNumbers;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public String getSpecsCode() {
        return specsCode;
    }

    public void setSpecsCode(String specsCode) {
        this.specsCode = specsCode;
    }

    public String getSpecsName() {
        return specsName;
    }

    public void setSpecsName(String specsName) {
        this.specsName = specsName;
    }

    public String getPresellType() {
        return presellType;
    }

    public void setPresellType(String presellType) {
        this.presellType = presellType;
    }

}
