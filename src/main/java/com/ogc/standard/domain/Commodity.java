/**
 * @Title Commodity.java 
 * @Package com.ogc.standard.domain 
 * @Description 
 * @author taojian  
 * @date 2018年11月2日 下午3:47:06 
 * @version V1.0   
 */
package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ogc.standard.dao.base.ABaseDO;

/** 
 * 商品
 * @author: taojian 
 * @since: 2018年11月2日 下午3:47:06 
 * @history:
 */
public class Commodity extends ABaseDO {

    private static final long serialVersionUID = 588631129311182401L;

    // 编号
    private String code;

    // 商品名
    private String name;

    // 大类编号
    private String parentCategoryCode;

    // 小类编号
    private String categoryCode;

    // 发货地
    private String deliverPlace;

    // 重量
    private String weight;

    // 物流方式
    private String logistics;

    // 月销量
    private Long monthSellCount;

    // 列表图
    private String listPic;

    // banner图
    private String bannerPic;

    // 描述
    private String description;

    // 店铺编号
    private String shopCode;

    // 商家编号
    private String sellUserId;

    // UI位置
    private String location;

    // UI次序
    private Long orderNo;

    // 状态（0 草稿，1 已提交待审核，2 审核不通过，3 审核通过待上架，4 已上架待购买，5 已下架）
    private String status;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // ******************DB********************

    // 规格列表
    private List<CommoditySpecs> specsList;

    // 产品最小价格
    private BigDecimal minPrice;

    // 产品最大价格
    private BigDecimal maxPrice;

    // 店铺名称
    private String shopName;

    public List<CommoditySpecs> getSpecsList() {
        return specsList;
    }

    public void setSpecsList(List<CommoditySpecs> specsList) {
        this.specsList = specsList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentCategoryCode() {
        return parentCategoryCode;
    }

    public void setParentCategoryCode(String parentCategoryCode) {
        this.parentCategoryCode = parentCategoryCode;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getDeliverPlace() {
        return deliverPlace;
    }

    public void setDeliverPlace(String deliverPlace) {
        this.deliverPlace = deliverPlace;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getLogistics() {
        return logistics;
    }

    public void setLogistics(String logistics) {
        this.logistics = logistics;
    }

    public Long getMonthSellCount() {
        return monthSellCount;
    }

    public void setMonthSellCount(Long monthSellCount) {
        this.monthSellCount = monthSellCount;
    }

    public String getListPic() {
        return listPic;
    }

    public void setListPic(String listPic) {
        this.listPic = listPic;
    }

    public String getBannerPic() {
        return bannerPic;
    }

    public void setBannerPic(String bannerPic) {
        this.bannerPic = bannerPic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
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

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getSellUserId() {
        return sellUserId;
    }

    public void setSellUserId(String sellUserId) {
        this.sellUserId = sellUserId;
    }

}
