package com.ogc.standard.dto.req;

import java.util.List;

/**
 * 分页查询认养权
 * @author: jiafr 
 * @since: 2018年9月28日 下午1:56:41 
 * @history:
 */
public class XN629205Req extends APageReq {

    private static final long serialVersionUID = 8479425657381483060L;

    // 认养订单编号
    private String orderCode;

    // 产品大类
    private String parentCategoryCode;

    private String categoryCode;

    private String productCode;

    private String treeNumber;

    // 状态(1待认养2认养中3已到期)
    private String status;

    // 当前持有人
    private String currentHolder;

    // 养护方
    private String ownerId;

    // 状态列表
    private List<String> statusList;

    // 订单编号模糊查
    private String orderCodeForQuery;

    public String getTreeNumber() {
        return treeNumber;
    }

    public void setTreeNumber(String treeNumber) {
        this.treeNumber = treeNumber;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

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

    public String getOrderCodeForQuery() {
        return orderCodeForQuery;
    }

    public void setOrderCodeForQuery(String orderCodeForQuery) {
        this.orderCodeForQuery = orderCodeForQuery;
    }

}
