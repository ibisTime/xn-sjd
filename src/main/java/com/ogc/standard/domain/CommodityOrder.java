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

    // 编号
    private String code;

    // 店铺编号
    private String shopCode;

    // 店主
    private String shopOwner;

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

    // 抵扣人民币
    private BigDecimal cnyDeductAmount;

    // 积分抵扣金额
    private BigDecimal jfDeductAmount;

    // 积分返点金额
    private BigDecimal backJfAmount;

    // 邮费
    private BigDecimal postalFee;

    // 地址编号
    private String addressCode;

    // 省份
    private String province;

    // 城市
    private String city;

    // 区
    private String district;

    // 具体地址
    private String detailAddress;

    // 物流公司
    private String logisticsCompany;

    // 物流单号
    private String logisticsNumber;

    // 发货人
    private String deliver;

    // 发货时间
    private Date deliverDatetime;

    // 收货人
    private String receiver;

    // 收货人手机
    private String receiverMobile;

    // 收货时间
    private Date receiverDatetime;

    // 状态
    private String status;

    // 结算状态
    private String settleStatus;

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

    private List<CommodityShopOrder> shopOrderList;

    // 支付流水
    private String jourCode;

    // 卖家
    private String sellersName;

    // 发货时间起
    private Date deliverDatetimeStart;

    // 发货时间止
    private Date deliverDatetimeEnd;

    // 下单人
    private String applyUserName;

    // 收货人
    private String receiverName;

    // 是否存在结算
    private String existsSettle;

    // 结算订单
    private List<Settle> settleList;

    public List<CommodityShopOrder> getShopOrderList() {
        return shopOrderList;
    }

    public void setShopOrderList(List<CommodityShopOrder> shopOrderList) {
        this.shopOrderList = shopOrderList;
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

    public String getJourCode() {
        return jourCode;
    }

    public void setJourCode(String jourCode) {
        this.jourCode = jourCode;
    }

    public String getSellersName() {
        return sellersName;
    }

    public void setSellersName(String sellersName) {
        this.sellersName = sellersName;
    }

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    public String getLogisticsNumber() {
        return logisticsNumber;
    }

    public void setLogisticsNumber(String logisticsNumber) {
        this.logisticsNumber = logisticsNumber;
    }

    public String getDeliver() {
        return deliver;
    }

    public void setDeliver(String deliver) {
        this.deliver = deliver;
    }

    public Date getDeliverDatetime() {
        return deliverDatetime;
    }

    public void setDeliverDatetime(Date deliverDatetime) {
        this.deliverDatetime = deliverDatetime;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public Date getReceiverDatetime() {
        return receiverDatetime;
    }

    public void setReceiverDatetime(Date receiverDatetime) {
        this.receiverDatetime = receiverDatetime;
    }

    public Date getDeliverDatetimeStart() {
        return deliverDatetimeStart;
    }

    public void setDeliverDatetimeStart(Date deliverDatetimeStart) {
        this.deliverDatetimeStart = deliverDatetimeStart;
    }

    public Date getDeliverDatetimeEnd() {
        return deliverDatetimeEnd;
    }

    public void setDeliverDatetimeEnd(Date deliverDatetimeEnd) {
        this.deliverDatetimeEnd = deliverDatetimeEnd;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public String getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(String settleStatus) {
        this.settleStatus = settleStatus;
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

    public String getShopOwner() {
        return shopOwner;
    }

    public void setShopOwner(String shopOwner) {
        this.shopOwner = shopOwner;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getExistsSettle() {
        return existsSettle;
    }

    public void setExistsSettle(String existsSettle) {
        this.existsSettle = existsSettle;
    }

    public List<Settle> getSettleList() {
        return settleList;
    }

    public void setSettleList(List<Settle> settleList) {
        this.settleList = settleList;
    }

    public BigDecimal getPostalFee() {
        return postalFee;
    }

    public void setPostalFee(BigDecimal postalFee) {
        this.postalFee = postalFee;
    }

}
