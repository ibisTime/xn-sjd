package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

    // 状态(0待支付1已取消2待认养3认养中4已到期)
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

    /*********DB properties*********/

    /*********辅助字段**********/

    private Date applyDatetimeStart;// 下单时间起

    private Date applyDatetimeEnd;// 下单时间止

    private Date startDatetimeStart;// 认养开始时间起

    private Date startDatetimeEnd;// 认养开始时间止

    private Date endDatetimeStart;// 认养结束时间起

    private Date endDatetimeEnd;// 认养结束时间止

    private Product product;

    private String ownerContractTemplate;// 产权方协议模板

    private List<AdoptOrderTree> adoptOrderTreeList;

    private List<Tree> treeList;

    private List<String> statusList;

    private List<Settle> settleList;

    public List<Settle> getSettleList() {
        return settleList;
    }

    public void setSettleList(List<Settle> settleList) {
        this.settleList = settleList;
    }

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

    public String getOwnerContractTemplate() {
        return ownerContractTemplate;
    }

    public void setOwnerContractTemplate(String ownerContractTemplate) {
        this.ownerContractTemplate = ownerContractTemplate;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public BigDecimal getCnyDeductAmount() {
        return cnyDeductAmount;
    }

    public void setCnyDeductAmount(BigDecimal cnyDeductAmount) {
        this.cnyDeductAmount = cnyDeductAmount;
    }

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

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
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

    public Date getPayDatetime() {
        return payDatetime;
    }

    public void setPayDatetime(Date payDatetime) {
        this.payDatetime = payDatetime;
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

    public BigDecimal getPayAmount() {
        return payAmount;
    }

}
