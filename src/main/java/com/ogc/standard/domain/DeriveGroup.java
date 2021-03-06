package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 预售派生组
* @author: silver 
* @since: 2018-11-03 17:45:20
* @history:
*/
public class DeriveGroup extends ABaseDO {
    private static final long serialVersionUID = 1819997462918814475L;

    // 编号
    private String code;

    // 原生组编号
    private String originalCode;

    // 产品编号
    private String productCode;

    // 产品名称
    private String productName;

    // 规格编号
    private String specsCode;

    // 规格名称
    private String specsName;

    // 品种
    private String variety;

    // 类型（定向/二维码/挂单）
    private String type;

    // 单价
    private BigDecimal price;

    // 初始数量
    private Integer originalQuantity;

    // 数量
    private Integer quantity;

    // 单位
    private String unit;

    // 挂单人
    private String creater;

    // 挂单时间
    private Date createDatetime;

    // 状态
    private String status;

    // 成交人
    private String claimant;

    // 成交时间
    private Date claimDatetime;

    // 波动幅度
    private Double wave;

    // 分享链接
    private String url;

    // 备注
    private String remark;

    /***************DB Properties***************/
    // 状态列表
    private List<String> statusList;

    // 预售产品
    private PresellProduct presellProduct;

    // 树木列表
    private List<PresellInventory> treeNumberList;

    // 查询人
    private String queryUserId;

    // 最小数量
    private String minQuantity;

    // 挂单人
    private User createrInfo;

    // 交易数量
    private Integer dealQuantity;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOriginalCode() {
        return originalCode;
    }

    public void setOriginalCode(String originalCode) {
        this.originalCode = originalCode;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClaimant() {
        return claimant;
    }

    public void setClaimant(String claimant) {
        this.claimant = claimant;
    }

    public Date getClaimDatetime() {
        return claimDatetime;
    }

    public void setClaimDatetime(Date claimDatetime) {
        this.claimDatetime = claimDatetime;
    }

    public Double getWave() {
        return wave;
    }

    public void setWave(Double wave) {
        this.wave = wave;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public PresellProduct getPresellProduct() {
        return presellProduct;
    }

    public void setPresellProduct(PresellProduct presellProduct) {
        this.presellProduct = presellProduct;
    }

    public List<PresellInventory> getTreeNumberList() {
        return treeNumberList;
    }

    public void setTreeNumberList(List<PresellInventory> treeNumberList) {
        this.treeNumberList = treeNumberList;
    }

    public String getQueryUserId() {
        return queryUserId;
    }

    public void setQueryUserId(String queryUserId) {
        this.queryUserId = queryUserId;
    }

    public String getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(String minQuantity) {
        this.minQuantity = minQuantity;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
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

    public User getCreaterInfo() {
        return createrInfo;
    }

    public void setCreaterInfo(User createrInfo) {
        this.createrInfo = createrInfo;
    }

    public Integer getOriginalQuantity() {
        return originalQuantity;
    }

    public void setOriginalQuantity(Integer originalQuantity) {
        this.originalQuantity = originalQuantity;
    }

    public Integer getDealQuantity() {
        return dealQuantity;
    }

    public void setDealQuantity(Integer dealQuantity) {
        this.dealQuantity = dealQuantity;
    }

}
