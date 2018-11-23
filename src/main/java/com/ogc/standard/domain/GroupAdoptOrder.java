package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

    // 规格编号
    private String productSpecsCode;

    // 规格名称
    private String productSpecsName;

    // 认养价格
    private BigDecimal price;

    // 认养开始时间
    private Date startDatetime;

    // 认养结束时间
    private Date endDatetime;

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

    // 支付金额
    private BigDecimal payAmount;

    // 支付时间
    private Date payDatetime;

    // 积分返点金额
    private BigDecimal backJfAmount;

    // 结算状态
    private String settleStatus;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    /**************DB properties*********/
    private Date applyDatetimeStart;// 下单时间起

    private Date applyDatetimeEnd;// 下单时间止

    private Date startDatetimeStart;// 认养开始时间起

    private Date startDatetimeEnd;// 认养开始时间止

    private Date endDatetimeStart;// 认养结束时间起

    private Date endDatetimeEnd;// 认养结束时间止

    // 产品信息
    private Product product;

    // 认养权
    private List<AdoptOrderTree> adoptOrderTreeList;

    // 树木列表
    private List<Tree> treeList;

    // 状态列表
    private List<String> statusList;

    // 认养年限
    private Integer adoptYear;

    // 下单人姓名
    private String applyUserName;

    // 树木编号列表
    private String treeNumbers;

    private List<Settle> settleList;

    // 是否存在结算
    private String existsSettle;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIdentifyCode() {
        return identifyCode;
    }

    public void setIdentifyCode(String identifyCode) {
        this.identifyCode = identifyCode;
    }

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getJfDeductAmount() {
        return jfDeductAmount;
    }

    public void setJfDeductAmount(BigDecimal jfDeductAmount) {
        this.jfDeductAmount = jfDeductAmount;
    }

    public Date getPayDatetime() {
        return payDatetime;
    }

    public void setPayDatetime(Date payDatetime) {
        this.payDatetime = payDatetime;
    }

    public BigDecimal getBackJfAmount() {
        return backJfAmount;
    }

    public void setBackJfAmount(BigDecimal backJfAmount) {
        this.backJfAmount = backJfAmount;
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

    public Date getStartDatetimeStart() {
        return startDatetimeStart;
    }

    public void setStartDatetimeStart(Date startDatetimeStart) {
        this.startDatetimeStart = startDatetimeStart;
    }

    public Date getStartDatetimeEnd() {
        return startDatetimeEnd;
    }

    public void setStartDatetimeEnd(Date startDatetimeEnd) {
        this.startDatetimeEnd = startDatetimeEnd;
    }

    public Date getEndDatetimeStart() {
        return endDatetimeStart;
    }

    public void setEndDatetimeStart(Date endDatetimeStart) {
        this.endDatetimeStart = endDatetimeStart;
    }

    public Date getEndDatetimeEnd() {
        return endDatetimeEnd;
    }

    public void setEndDatetimeEnd(Date endDatetimeEnd) {
        this.endDatetimeEnd = endDatetimeEnd;
    }

    public BigDecimal getCnyDeductAmount() {
        return cnyDeductAmount;
    }

    public void setCnyDeductAmount(BigDecimal cnyDeductAmount) {
        this.cnyDeductAmount = cnyDeductAmount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<AdoptOrderTree> getAdoptOrderTreeList() {
        return adoptOrderTreeList;
    }

    public void setAdoptOrderTreeList(List<AdoptOrderTree> adoptOrderTreeList) {
        this.adoptOrderTreeList = adoptOrderTreeList;
    }

    public List<Tree> getTreeList() {
        return treeList;
    }

    public void setTreeList(List<Tree> treeList) {
        this.treeList = treeList;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public Integer getAdoptYear() {
        return adoptYear;
    }

    public void setAdoptYear(Integer adoptYear) {
        this.adoptYear = adoptYear;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public String getTreeNumbers() {
        return treeNumbers;
    }

    public void setTreeNumbers(String treeNumbers) {
        this.treeNumbers = treeNumbers;
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

    public String getProductSpecsName() {
        return productSpecsName;
    }

    public void setProductSpecsName(String productSpecsName) {
        this.productSpecsName = productSpecsName;
    }

    public String getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(String settleStatus) {
        this.settleStatus = settleStatus;
    }

    public List<Settle> getSettleList() {
        return settleList;
    }

    public void setSettleList(List<Settle> settleList) {
        this.settleList = settleList;
    }

    public String getExistsSettle() {
        return existsSettle;
    }

    public void setExistsSettle(String existsSettle) {
        this.existsSettle = existsSettle;
    }

}
