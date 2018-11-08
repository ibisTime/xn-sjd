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

import com.ogc.standard.dao.base.ABaseDO;

/** 
 * 商品订单（单店铺）
 * @author: taojian 
 * @since: 2018年11月6日 上午11:29:58 
 * @history:
 */
public class CommodityOrderDetail extends ABaseDO {

    private static final long serialVersionUID = 8572770333835172098L;

    // ****************DB*****************

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

    // 数量
    private Long quantity;

    // 价格
    private BigDecimal price;

    // 总金额
    private BigDecimal amount;

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

    // ****************DB*****************

    private Commodity commodity;

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

}
