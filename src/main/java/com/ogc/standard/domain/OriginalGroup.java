package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 预售原生组
* @author: silver 
* @since: 2018-11-03 17:42:25
* @history:
*/
public class OriginalGroup extends ABaseDO {

    private static final long serialVersionUID = 5740923004809992866L;

    // 编号
    private String code;

    // 产权方编号
    private String belongPartId;

    // 订单/父级原生组编号
    private String orderCode;

    // 产品编号
    private String productCode;

    // 产品名称
    private String productName;

    // 规格编号
    private String specsCode;

    // 规格名称
    private String specsName;

    // 归属人
    private String ownerId;

    // 总价
    private BigDecimal price;

    // 数量
    private Integer quantity;

    // 挂单数量
    private Integer presellQuantity;

    // 提货中数量
    private Integer receivingQuantity;

    // 已提货数量
    private Integer receivedQuantity;

    // 单位
    private String unit;

    // 认养开始时间
    private Date adoptStartDatetime;

    // 认养结束时间
    private Date adoptEndDatetime;

    // 发货时间
    private Date deliverDatetime;

    // 状态
    private String status;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    /************DB Properties************/
    // 认养开始开始时间
    private Date adoptStartStartDatetime;

    // 认养开始结束时间
    private Date adoptStartEndDatetime;

    // 认养结束开始时间
    private Date adoptEndStartDatetime;

    // 认养结束结束时间
    private Date adoptEndEndDatetime;

    // 预售产品
    private PresellProduct presellProduct;

    // 树木列表
    private List<String> treeNumberList;

    // 最小数量
    private String minQuantity;

    // 所属人信息
    private User ownerInfo;

    // 开始发货时间
    private Date deliverDatetimeStart;

    // 结束发货时间
    private Date deliverDatetimeEnd;

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

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerId() {
        return ownerId;
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

    public Integer getPresellQuantity() {
        return presellQuantity;
    }

    public void setPresellQuantity(Integer presellQuantity) {
        this.presellQuantity = presellQuantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public Date getAdoptStartDatetime() {
        return adoptStartDatetime;
    }

    public void setAdoptStartDatetime(Date adoptStartDatetime) {
        this.adoptStartDatetime = adoptStartDatetime;
    }

    public Date getAdoptEndDatetime() {
        return adoptEndDatetime;
    }

    public void setAdoptEndDatetime(Date adoptEndDatetime) {
        this.adoptEndDatetime = adoptEndDatetime;
    }

    public Date getAdoptStartStartDatetime() {
        return adoptStartStartDatetime;
    }

    public void setAdoptStartStartDatetime(Date adoptStartStartDatetime) {
        this.adoptStartStartDatetime = adoptStartStartDatetime;
    }

    public Date getAdoptStartEndDatetime() {
        return adoptStartEndDatetime;
    }

    public void setAdoptStartEndDatetime(Date adoptStartEndDatetime) {
        this.adoptStartEndDatetime = adoptStartEndDatetime;
    }

    public Date getAdoptEndStartDatetime() {
        return adoptEndStartDatetime;
    }

    public void setAdoptEndStartDatetime(Date adoptEndStartDatetime) {
        this.adoptEndStartDatetime = adoptEndStartDatetime;
    }

    public Date getAdoptEndEndDatetime() {
        return adoptEndEndDatetime;
    }

    public void setAdoptEndEndDatetime(Date adoptEndEndDatetime) {
        this.adoptEndEndDatetime = adoptEndEndDatetime;
    }

    public PresellProduct getPresellProduct() {
        return presellProduct;
    }

    public void setPresellProduct(PresellProduct presellProduct) {
        this.presellProduct = presellProduct;
    }

    public List<String> getTreeNumberList() {
        return treeNumberList;
    }

    public void setTreeNumberList(List<String> treeNumberList) {
        this.treeNumberList = treeNumberList;
    }

    public String getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(String minQuantity) {
        this.minQuantity = minQuantity;
    }

    public Integer getReceivingQuantity() {
        return receivingQuantity;
    }

    public void setReceivingQuantity(Integer receivingQuantity) {
        this.receivingQuantity = receivingQuantity;
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

    public User getOwnerInfo() {
        return ownerInfo;
    }

    public void setOwnerInfo(User ownerInfo) {
        this.ownerInfo = ownerInfo;
    }

    public String getBelongPartId() {
        return belongPartId;
    }

    public void setBelongPartId(String belongPartId) {
        this.belongPartId = belongPartId;
    }

    public Date getDeliverDatetime() {
        return deliverDatetime;
    }

    public void setDeliverDatetime(Date deliverDatetime) {
        this.deliverDatetime = deliverDatetime;
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

    public Integer getReceivedQuantity() {
        return receivedQuantity;
    }

    public void setReceivedQuantity(Integer receivedQuantity) {
        this.receivedQuantity = receivedQuantity;
    }

}
