package com.ogc.standard.dto.req;

import java.util.List;

/**
 * 列表查询认养权
 * @author: silver 
 * @since: 2018年9月26日 下午5:54:37 
 * @history:
 */
public class XN629207Req extends AListReq {

    private static final long serialVersionUID = -2734869162207465263L;

    // 认养订单编号
    private String orderCode;

    // 产品大类
    private String parentCategoryCode;

    // 分类编号
    private String categoryCode;

    // 状态(1待认养2认养中3已到期)
    private String status;

    // 当前持有人
    private String currentHolder;

    // 产权方
    private String ownerId;

    // 状态列表
    private List<String> statusList;

    // 树木级别
    private String treeLevel;

    // 品种
    private String variety;

    // 省
    private String province;

    // 市
    private String city;

    // 区
    private String area;

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentHolder() {
        return currentHolder;
    }

    public void setCurrentHolder(String currentHolder) {
        this.currentHolder = currentHolder;
    }

    public String getParentCategoryCode() {
        return parentCategoryCode;
    }

    public void setParentCategoryCode(String parentCategoryCode) {
        this.parentCategoryCode = parentCategoryCode;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(String treeLevel) {
        this.treeLevel = treeLevel;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

}
