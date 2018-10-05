package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 认养权
* @author: jiafr 
* @since: 2018-09-28 13:28:54
* @history:
*/
public class AdoptOrderTree extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 认养订单编号
    private String orderCode;

    // 分类
    private String categoryCode;

    // 产品编号
    private String productCode;

    // 古树编号
    private String treeNumber;

    // 认养开始时间
    private Date startDatetime;

    // 认养结束时间
    private Date endDatetime;

    // 认养金额
    private BigDecimal amount;

    // 状态(1待认养2认养中3已到期4已赠送)
    private String status;

    // 当前持有人
    private String currentHolder;

    // 备注
    private String remark;

    // ***************db properties***************
    private Tree tree;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setTreeNumber(String treeNumber) {
        this.treeNumber = treeNumber;
    }

    public String getTreeNumber() {
        return treeNumber;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setCurrentHolder(String currentHolder) {
        this.currentHolder = currentHolder;
    }

    public String getCurrentHolder() {
        return currentHolder;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

}
