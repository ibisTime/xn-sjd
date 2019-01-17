/**
 * @Title XN629706Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 上午10:00:57 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

/** 
 * @author: taojian 
 * @since: 2018年11月6日 上午10:00:57 
 * @history:
 */
public class XN629708Req {

    private String name;

    private String parentCategoryCode;

    private String categoryCode;

    private String deliverPlace;

    private String weight;

    private String logistics;

    private String shopCode;

    private String location;

    private String status;

    // 产品最小价格
    private String minSpecPrice;

    // 产品最大价格
    private String maxSpecPrice;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMinSpecPrice() {
        return minSpecPrice;
    }

    public void setMinSpecPrice(String minSpecPrice) {
        this.minSpecPrice = minSpecPrice;
    }

    public String getMaxSpecPrice() {
        return maxSpecPrice;
    }

    public void setMaxSpecPrice(String maxSpecPrice) {
        this.maxSpecPrice = maxSpecPrice;
    }

}
