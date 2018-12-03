package com.ogc.standard.domain;

import java.math.BigDecimal;
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

    // 销售分类
    private String sellType;

    // 定向类型(1=等级 2=个人)
    private String directType;

    // 定向对象
    private String directObject;

    // 产权方编号
    private String ownerId;

    // 产品大类
    private String parentCategoryCode;

    // 产品小类
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

    // 产品描述
    private String description;

    // 省
    private String province;

    // 市
    private String city;

    // 区
    private String area;

    // 镇
    private String town;

    // 募集开始时间(集体售卖开始时间/捐赠开始时间)
    private Date raiseStartDatetime;

    // 募集结束时间(集体售卖结束时间/捐赠结束时间)
    private Date raiseEndDatetime;

    // 募集总量
    private Integer raiseCount;

    // 已募集数量
    private Integer nowCount;

    // 最大积分抵扣比例
    private double maxJfdkRate;

    // 当前识别码（集体）
    private String identifyCode;

    // 识别码失效时间（集体）
    private Date idInvalidDatetime;

    // 当前集体订单规格（集体）
    private String specsCode;

    // 集体第一认养人
    private String collectFirstUser;

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

    // 树木数量
    private Integer treeTotalCount;

    // 树木列表
    private List<Tree> treeList;

    // 剩余数量
    private Integer treeRemainCount;

    // 产品大类名称
    private String parentCategoryName;

    // 产品小类名称
    private String categoryName;

    // 产品最小价格
    private BigDecimal minPrice;

    // 产品最大价格
    private BigDecimal maxPrice;

    // 产权方信息
    private SYSUser ownerInfo;

    // 识别码失效开始时间
    private Date idInvalidDatetimeStart;

    // 识别码失效结束时间
    private Date idInvalidDatetimeEnd;

    // 集体产品是否认养中
    private String isAdopting;

    // 状态列表
    private List<String> statusList;

    // 捐赠认养开始时间
    private Date startDatetime;

    // 捐赠认养结束时间
    private Date endDatetime;

    // 定向对象名称
    private String directObjectName;

    // 销售类型列表
    private List<String> sellTypeList;

    // 集体第一认养人
    private String collectFirstUserName;

    public String getDirectType() {
        return directType;
    }

    public void setDirectType(String directType) {
        this.directType = directType;
    }

    public String getDirectObject() {
        return directObject;
    }

    public void setDirectObject(String directObject) {
        this.directObject = directObject;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTreeTotalCount() {
        return treeTotalCount;
    }

    public void setTreeTotalCount(Integer treeTotalCount) {
        this.treeTotalCount = treeTotalCount;
    }

    public List<Tree> getTreeList() {
        return treeList;
    }

    public void setTreeList(List<Tree> treeList) {
        this.treeList = treeList;
    }

    public Integer getTreeRemainCount() {
        return treeRemainCount;
    }

    public void setTreeRemainCount(Integer treeRemainCount) {
        this.treeRemainCount = treeRemainCount;
    }

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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getParentCategoryCode() {
        return parentCategoryCode;
    }

    public void setParentCategoryCode(String parentCategoryCode) {
        this.parentCategoryCode = parentCategoryCode;
    }

    public String getParentCategoryName() {
        return parentCategoryName;
    }

    public void setParentCategoryName(String parentCategoryName) {
        this.parentCategoryName = parentCategoryName;
    }

    public SYSUser getOwnerInfo() {
        return ownerInfo;
    }

    public void setOwnerInfo(SYSUser ownerInfo) {
        this.ownerInfo = ownerInfo;
    }

    public String getIdentifyCode() {
        return identifyCode;
    }

    public void setIdentifyCode(String identifyCode) {
        this.identifyCode = identifyCode;
    }

    public Date getIdInvalidDatetime() {
        return idInvalidDatetime;
    }

    public void setIdInvalidDatetime(Date idInvalidDatetime) {
        this.idInvalidDatetime = idInvalidDatetime;
    }

    public Date getIdInvalidDatetimeStart() {
        return idInvalidDatetimeStart;
    }

    public void setIdInvalidDatetimeStart(Date idInvalidDatetimeStart) {
        this.idInvalidDatetimeStart = idInvalidDatetimeStart;
    }

    public Date getIdInvalidDatetimeEnd() {
        return idInvalidDatetimeEnd;
    }

    public void setIdInvalidDatetimeEnd(Date idInvalidDatetimeEnd) {
        this.idInvalidDatetimeEnd = idInvalidDatetimeEnd;
    }

    public String getIsAdopting() {
        return isAdopting;
    }

    public void setIsAdopting(String isAdopting) {
        this.isAdopting = isAdopting;
    }

    public String getSpecsCode() {
        return specsCode;
    }

    public void setSpecsCode(String specsCode) {
        this.specsCode = specsCode;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    public double getMaxJfdkRate() {
        return maxJfdkRate;
    }

    public void setMaxJfdkRate(double maxJfdkRate) {
        this.maxJfdkRate = maxJfdkRate;
    }

    public String getDirectObjectName() {
        return directObjectName;
    }

    public void setDirectObjectName(String directObjectName) {
        this.directObjectName = directObjectName;
    }

    public List<String> getSellTypeList() {
        return sellTypeList;
    }

    public void setSellTypeList(List<String> sellTypeList) {
        this.sellTypeList = sellTypeList;
    }

    public String getCollectFirstUser() {
        return collectFirstUser;
    }

    public void setCollectFirstUser(String collectFirstUser) {
        this.collectFirstUser = collectFirstUser;
    }

    public String getCollectFirstUserName() {
        return collectFirstUserName;
    }

    public void setCollectFirstUserName(String collectFirstUserName) {
        this.collectFirstUserName = collectFirstUserName;
    }

}
