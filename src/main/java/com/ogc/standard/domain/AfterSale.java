/**
 * @Title AfterSale.java 
 * @Package com.ogc.standard.domain 
 * @Description 
 * @author taojian  
 * @date 2018年11月7日 下午5:54:44 
 * @version V1.0   
 */
package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ogc.standard.dao.base.ABaseDO;

/** 
 * 售后订单
 * @author: taojian 
 * @since: 2018年11月7日 下午5:54:44 
 * @history:
 */
public class AfterSale extends ABaseDO {

    private static final long serialVersionUID = -1290069067222204355L;

    // 编号
    private String code;

    // 店铺编号
    private String shopCode;

    // 订单明细编号
    private String orderDetailCode;

    // 类型（0 退款，1退款退货）
    private String type;

    // 状态（0 待处理，1 处理通过，2 处理不通过，3 待收货，4 已完成）
    private String status;

    // 退款金额
    private BigDecimal refundAmount;

    // 退款原因
    private String refundReason;

    // 留言
    private String message;

    // 物流公司
    private String logisticsCompany;

    // 物流单号
    private String logisticsNumber;

    // 发货人
    private String deliver;

    // 发货数量
    private Long deliverCount;

    // 发货时间
    private Date deliverDatetime;

    // 地址编号
    private String addressCode;

    // 收货人
    private String receiver;

    // 收货人手机号
    private String receiverMobile;

    // 收货时间
    private Date receiverDatetime;

    // *************DB****************

    // 状态列表
    private List<String> statusList;

    private CommodityOrderDetail orderDetail;

    public CommodityOrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(CommodityOrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrderDetailCode() {
        return orderDetailCode;
    }

    public void setOrderDetailCode(String orderDetailCode) {
        this.orderDetailCode = orderDetailCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
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

    public Long getDeliverCount() {
        return deliverCount;
    }

    public void setDeliverCount(Long deliverCount) {
        this.deliverCount = deliverCount;
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

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
