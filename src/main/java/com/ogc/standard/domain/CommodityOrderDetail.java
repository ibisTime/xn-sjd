package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ogc.standard.dao.base.ABaseDO;

/** 
 * 订单明细
 * @author: taojian 
 * @since: 2018年11月6日 上午11:29:58 
 * @history:
 */
public class CommodityOrderDetail extends ABaseDO {

    private static final long serialVersionUID = 8572770333835172098L;

    // 编号
    private String code;

    // 订单编号
    private String orderCode;

    // 店铺编号
    private String shopCode;

    // 商品编号
    private String commodityCode;

    // 商品名称
    private String commodityName;

    // 规格编号
    private Long specsId;

    // 规格名称
    private String specsName;

    // 下单人
    private String applyUser;

    // 下单时间
    private Date applyDatetime;

    // 数量
    private Long quantity;

    // 价格
    private BigDecimal price;

    // 总金额
    private BigDecimal amount;

    // 最大积分抵扣比例
    private double maxJfdkRate;

    // 抵扣人民币
    private BigDecimal cnyDeductAmount;

    // 积分抵扣金额
    private BigDecimal jfDeductAmount;

    // 积分返点金额
    private BigDecimal backJfAmount;

    // 支付金额
    private BigDecimal payAmount;

    // 支付方式
    private String payType;

    // 列表图
    private String listPic;

    // 物流公司
    private String logisticsCompany;

    // 物流单号
    private String logisticsNumber;

    // 发货人
    private String deliver;

    // 发货时间
    private Date deliverDatetime;

    // 地址编号
    private String addressCode;

    // 收货人
    private String receiver;

    // 收货人手机
    private String receiverMobile;

    // 收货时间
    private Date receiverDatetime;

    // 状态
    private String status;

    // 售后状态
    private String afterSaleStatus;

    // 更新时间
    private Date updateDatetime;

    // ****************DB*****************

    // 发货时间起
    private Date deliverDatetimeStart;

    // 发货时间止
    private Date deliverDatetimeEnd;

    // 商品
    private Commodity commodity;

    // 地址
    private Address address;

    // 店铺名称
    private String shopName;

    // 卖家
    private String sellerName;

    // 流水号
    private String jourCode;

    // 物流方式
    private String logistics;

    // 状态列表
    private List<String> statusList;

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

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public Long getSpecsId() {
        return specsId;
    }

    public void setSpecsId(Long specsId) {
        this.specsId = specsId;
    }

    public String getSpecsName() {
        return specsName;
    }

    public void setSpecsName(String specsName) {
        this.specsName = specsName;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getListPic() {
        return listPic;
    }

    public void setListPic(String listPic) {
        this.listPic = listPic;
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

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
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

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getLogistics() {
        return logistics;
    }

    public void setLogistics(String logistics) {
        this.logistics = logistics;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public Date getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    public double getMaxJfdkRate() {
        return maxJfdkRate;
    }

    public void setMaxJfdkRate(double maxJfdkRate) {
        this.maxJfdkRate = maxJfdkRate;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getAfterSaleStatus() {
        return afterSaleStatus;
    }

    public void setAfterSaleStatus(String afterSaleStatus) {
        this.afterSaleStatus = afterSaleStatus;
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

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

}
