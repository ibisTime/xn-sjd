package com.ogc.standard.dto.req;

import java.util.List;

/**
 * 分页查询产品分类
 * @author: silver 
 * @since: 2018年9月26日 下午5:47:38 
 * @history:
 */
public class XN629025Req extends APageReq {

    private static final long serialVersionUID = 3564125020784062589L;

    // 编号
    private String code;

    // 名称
    private String name;

    // 销售分类（0个人/1定向/2捐赠/3集体）
    private String sellType;

    // 产权方编号
    private String ownerId;

    // UI位置
    private String location;

    // 产品大类
    private String parentCategoryCode;

    // 产品分类
    private String categoryCode;

    // 募集开始开始时间
    private String raiseStartStartDatetime;

    // 募集开始结束时间
    private String raiseStartEndDatetime;

    // 募集结束开始时间
    private String raiseEndStartDatetime;

    // 募集结束结束时间
    private String raiseEndEndDatetime;

    // 状态（0草稿/1已提交待审核/2审核不通过/3审核通过待上架/4已上架待认养/5已锁定/6已认养可下架/7已下架）
    private String status;

    // 状态列表
    private List<String> statusList;

    // 品种
    private String variety;

    // 省
    private String province;

    // 市
    private String city;

    // 区
    private String area;

    // 认养状态（0不可认养/1可认养）
    private String adoptStatus;

    // 树级
    private String treeLevel;

    // 查询人(筛选定向用户)
    private String queryUserId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSellType() {
        return sellType;
    }

    public void setSellType(String sellType) {
        this.sellType = sellType;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getRaiseStartStartDatetime() {
        return raiseStartStartDatetime;
    }

    public void setRaiseStartStartDatetime(String raiseStartStartDatetime) {
        this.raiseStartStartDatetime = raiseStartStartDatetime;
    }

    public String getRaiseStartEndDatetime() {
        return raiseStartEndDatetime;
    }

    public void setRaiseStartEndDatetime(String raiseStartEndDatetime) {
        this.raiseStartEndDatetime = raiseStartEndDatetime;
    }

    public String getRaiseEndStartDatetime() {
        return raiseEndStartDatetime;
    }

    public void setRaiseEndStartDatetime(String raiseEndStartDatetime) {
        this.raiseEndStartDatetime = raiseEndStartDatetime;
    }

    public String getRaiseEndEndDatetime() {
        return raiseEndEndDatetime;
    }

    public void setRaiseEndEndDatetime(String raiseEndEndDatetime) {
        this.raiseEndEndDatetime = raiseEndEndDatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getParentCategoryCode() {
        return parentCategoryCode;
    }

    public void setParentCategoryCode(String parentCategoryCode) {
        this.parentCategoryCode = parentCategoryCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
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

    public String getAdoptStatus() {
        return adoptStatus;
    }

    public void setAdoptStatus(String adoptStatus) {
        this.adoptStatus = adoptStatus;
    }

    public String getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(String treeLevel) {
        this.treeLevel = treeLevel;
    }

    public String getQueryUserId() {
        return queryUserId;
    }

    public void setQueryUserId(String queryUserId) {
        this.queryUserId = queryUserId;
    }

}
