/**
 * @Title XN629700Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年11月2日 下午4:37:40 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.ogc.standard.domain.CommoditySpecs;

/** 
 * @author: taojian 
 * @since: 2018年11月2日 下午4:37:40 
 * @history:
 */
public class XN629700Req {
    // 商品名
    @NotBlank
    private String name;

    // 大类编号
    @NotBlank
    private String parentCategoryCode;

    // 小类编号
    @NotBlank
    private String categoryCode;

    // 发货地
    @NotBlank
    private String deliverPlace;

    // 重量
    @NotBlank
    private String weight;

    // 最大积分抵扣比例
    private String maxJfdkRate;

    // 单邮费数量
    private String singlePostageCount;

    // 原价
    @NotBlank
    private String originalPrice;

    // 原产地
    private String originalPlace;

    // 物流方式
    @NotBlank
    private String logistics;

    // 列表图
    @NotBlank
    private String listPic;

    // banner 图
    @NotBlank
    private String bannerPic;

    // 描述
    @NotBlank
    private String description;

    // 店铺编号
    @NotBlank
    private String shopCode;

    // 规格列表
    @NotEmpty
    private List<CommoditySpecs> specsList;

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

    public List<CommoditySpecs> getSpecsList() {
        return specsList;
    }

    public void setSpecsList(List<CommoditySpecs> specsList) {
        this.specsList = specsList;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getMaxJfdkRate() {
        return maxJfdkRate;
    }

    public void setMaxJfdkRate(String maxJfdkRate) {
        this.maxJfdkRate = maxJfdkRate;
    }

    public String getSinglePostageCount() {
        return singlePostageCount;
    }

    public void setSinglePostageCount(String singlePostageCount) {
        this.singlePostageCount = singlePostageCount;
    }

    public String getOriginalPlace() {
        return originalPlace;
    }

    public void setOriginalPlace(String originalPlace) {
        this.originalPlace = originalPlace;
    }

}
