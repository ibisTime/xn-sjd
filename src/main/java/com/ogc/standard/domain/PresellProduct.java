package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 预售订单
* @author: silver 
* @since: 2018-11-02 16:50:18
* @history:
*/
public class PresellProduct extends ABaseDO {

    private static final long serialVersionUID = 1021931349495168883L;

    // 编号
    private String code;

    // 名称
    private String name;

    // 产权方编号
    private String ownerId;

    // 产品大类
    private String parentCategoryCode;

    // 产品分类
    private String categoryCode;

    // 列表图片
    private String listPic;

    // 详情banner图
    private String bannerPic;

    // 图文详情
    private String description;

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

    // 经度
    private String longitude;

    // 维度
    private String latitude;

    // 单颗树产量
    private Integer singleOutput;

    // 包装单位
    private String packUnit;

    // 包装重量
    private Double packWeight;

    // 树木数量
    private Integer treeCount;

    // 产出总量
    private Integer totalOutput;

    // 认养开始时间
    private Date adoptStartDatetime;

    // 认养结束时间
    private Date adoptEndDatetime;

    // 认养年限
    private Integer adoptYear;

    // 收货时间
    private Date harvestDatetime;

    // 已预售数量
    private Integer nowCount;

    // UI位置
    private String location;

    // UI次序
    private Integer orderNo;

    // 状态
    private String status;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    /****************DB Properties****************/
    private List<String> statusList;

    // 预售规格
    private List<PresellSpecs> presellSpecsList;

    // 树木列表
    private List<Tree> treeList;

    // 产品最小价格
    private BigDecimal minPrice;

    // 产品最大价格
    private BigDecimal maxPrice;

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

    public String getOriginPlace() {
        return originPlace;
    }

    public void setOriginPlace(String originPlace) {
        this.originPlace = originPlace;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Integer getSingleOutput() {
        return singleOutput;
    }

    public void setSingleOutput(Integer singleOutput) {
        this.singleOutput = singleOutput;
    }

    public String getPackUnit() {
        return packUnit;
    }

    public void setPackUnit(String packUnit) {
        this.packUnit = packUnit;
    }

    public Double getPackWeight() {
        return packWeight;
    }

    public void setPackWeight(Double packWeight) {
        this.packWeight = packWeight;
    }

    public Integer getTreeCount() {
        return treeCount;
    }

    public void setTreeCount(Integer treeCount) {
        this.treeCount = treeCount;
    }

    public Integer getTotalOutput() {
        return totalOutput;
    }

    public void setTotalOutput(Integer totalOutput) {
        this.totalOutput = totalOutput;
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

    public Integer getAdoptYear() {
        return adoptYear;
    }

    public void setAdoptYear(Integer adoptYear) {
        this.adoptYear = adoptYear;
    }

    public Date getHarvestDatetime() {
        return harvestDatetime;
    }

    public void setHarvestDatetime(Date harvestDatetime) {
        this.harvestDatetime = harvestDatetime;
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

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public List<PresellSpecs> getPresellSpecsList() {
        return presellSpecsList;
    }

    public void setPresellSpecsList(List<PresellSpecs> presellSpecsList) {
        this.presellSpecsList = presellSpecsList;
    }

    public List<Tree> getTreeList() {
        return treeList;
    }

    public void setTreeList(List<Tree> treeList) {
        this.treeList = treeList;
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

    public String getParentCategoryCode() {
        return parentCategoryCode;
    }

    public void setParentCategoryCode(String parentCategoryCode) {
        this.parentCategoryCode = parentCategoryCode;
    }

}
