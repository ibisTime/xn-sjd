/**
 * @Title CommodityOrder.java 
 * @Package com.ogc.standard.domain 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 上午11:29:58 
 * @version V1.0   
 */
package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ogc.standard.dao.base.ABaseDO;

/** 
 * 商品订单（多店铺）
 * @author: taojian 
 * @since: 2018年11月6日 上午11:29:58 
 * @history:
 */
public class CommodityOrder extends ABaseDO {

    private static final long serialVersionUID = 8572770333835172098L;

    // ****************DB*****************

    // 编号
    private String code;

    // 订单金额
    private BigDecimal amount;

    // 商品数量
    private Long quantity;

    // 下单人
    private String applyUser;

    // 下单时间
    private Date applyDatetime;

    // 下单说明
    private String applyNote;

    // 配送方式
    private String expressType;

    // 支付方式
    private String payType;

    // 支付组号
    private String payGroup;

    // 支付渠道号
    private String payCode;

    // 支付时间
    private Date payDatetime;

    // 支付金额
    private BigDecimal payAmount;

    // 状态
    private String status;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // ****************DB*****************

    // 下单时间起
    private Date applyDatetimeStart;

    // 下单时间止
    private Date applyDatetimeEnd;

    private List<CommodityOrderDetail> detailList;

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

    public List<CommodityOrderDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<CommodityOrderDetail> detailList) {
        this.detailList = detailList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
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

    public String getApplyNote() {
        return applyNote;
    }

    public void setApplyNote(String applyNote) {
        this.applyNote = applyNote;
    }

    public String getExpressType() {
        return expressType;
    }

    public void setExpressType(String expressType) {
        this.expressType = expressType;
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

    public Date getPayDatetime() {
        return payDatetime;
    }

    public void setPayDatetime(Date payDatetime) {
        this.payDatetime = payDatetime;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

}
