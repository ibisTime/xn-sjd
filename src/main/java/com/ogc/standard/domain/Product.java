package com.ogc.standard.domain;

import java.util.Date;
import java.util.List;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 认养产品
* @author: silver 
* @since: 2018-09-26 12:18:37
* @history:
*/
public class Product extends ABaseDO {

    private static final long serialVersionUID = 7658073864570568725L;

    // 编号
    private String code;

    // 名称
    private String name;

    // 销售分类（0个人/1定向/2捐赠/3集体）
    private String sellType;

    // 产权方编号
    private String ownerId;

    // 产品分类
    private String categoryCode;

    // 列表图片
    private String listPic;

    // 详情banner图
    private String bannerPic;

    // 产地
    private String originPlace;

    // 学名
    private String scientificName;

    // 品种
    private String variety;

    // 级别
    private String rank;

    // 省
    private String province;

    // 市
    private String city;

    // 区
    private String area;

    // 镇
    private String town;

    // 募集开始时间
    private Date raiseStartDatetime;

    // 募集结束时间
    private Date raiseEndDatetime;

    // 募集总量
    private Integer raiseCount;

    // 已募集数量
    private Integer nowCount;

    // UI位置
    private String location;

    // UI次序
    private Integer orderNo;

    // 状态（0草稿/1已提交待审核/2审核不通过/3审核通过待上架/4已上架待认养/5已锁定/6已认养可下架/7已下架）
    private String status;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    // *************DB Properties*************
    // 募集开始开始时间
    private Date raiseStartStartDatetime;

    // 募集开始结束时间
    private Date raiseStartEndDatetime;

    // 募集结束开始时间
    private Date raiseEndStartDatetime;

    // 募集结束结束时间
    private Date raiseEndEndDatetime;

    // 产品规格列表
    private List<ProductSpecs> productSpecsList;

    // 树木列表
    private List<Tree> treeList;

    // 树木数量
    private Integer treeCount;

    // 产品类型名称
    private String categoryName;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSellType(String sellType) {
        this.sellType = sellType;
    }

    public String getSellType() {
        return sellType;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setListPic(String listPic) {
        this.listPic = listPic;
    }

    public String getListPic() {
        return listPic;
    }

    public void setBannerPic(String bannerPic) {
        this.bannerPic = bannerPic;
    }

    public String getBannerPic() {
        return bannerPic;
    }

    public void setOriginPlace(String originPlace) {
        this.originPlace = originPlace;
    }

    public String getOriginPlace() {
        return originPlace;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getVariety() {
        return variety;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince() {
        return province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getTown() {
        return town;
    }

    public Date getRaiseStartDatetime() {
        return raiseStartDatetime;
    }

    public void setRaiseStartDatetime(Date raiseStartDatetime) {
        this.raiseStartDatetime = raiseStartDatetime;
    }

    public Date getRaiseEndDatetime() {
        return raiseEndDatetime;
    }

    public void setRaiseEndDatetime(Date raiseEndDatetime) {
        this.raiseEndDatetime = raiseEndDatetime;
    }

    public Integer getRaiseCount() {
        return raiseCount;
    }

    public void setRaiseCount(Integer raiseCount) {
        this.raiseCount = raiseCount;
    }

    public Integer getNowCount() {
        return nowCount;
    }

    public void setNowCount(Integer nowCount) {
        this.nowCount = nowCount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
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

    public Date getRaiseStartStartDatetime() {
        return raiseStartStartDatetime;
    }

    public void setRaiseStartStartDatetime(Date raiseStartStartDatetime) {
        this.raiseStartStartDatetime = raiseStartStartDatetime;
    }

    public Date getRaiseStartEndDatetime() {
        return raiseStartEndDatetime;
    }

    public void setRaiseStartEndDatetime(Date raiseStartEndDatetime) {
        this.raiseStartEndDatetime = raiseStartEndDatetime;
    }

    public Date getRaiseEndStartDatetime() {
        return raiseEndStartDatetime;
    }

    public void setRaiseEndStartDatetime(Date raiseEndStartDatetime) {
        this.raiseEndStartDatetime = raiseEndStartDatetime;
    }

    public Date getRaiseEndEndDatetime() {
        return raiseEndEndDatetime;
    }

    public void setRaiseEndEndDatetime(Date raiseEndEndDatetime) {
        this.raiseEndEndDatetime = raiseEndEndDatetime;
    }

    public List<ProductSpecs> getProductSpecsList() {
        return productSpecsList;
    }

    public void setProductSpecsList(List<ProductSpecs> productSpecsList) {
        this.productSpecsList = productSpecsList;
    }

    public List<Tree> getTreeList() {
        return treeList;
    }

    public void setTreeList(List<Tree> treeList) {
        this.treeList = treeList;
    }

    public Integer getTreeCount() {
        return treeCount;
    }

    public void setTreeCount(Integer treeCount) {
        this.treeCount = treeCount;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
