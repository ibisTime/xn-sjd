package com.ogc.standard.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 新增预售产品
 * @author: silver 
 * @since: Nov 3, 2018 9:56:28 AM 
 * @history:
 */
public class XN629400Req extends BaseReq {
    private static final long serialVersionUID = -6875342580769258538L;

    // 名称
    @NotBlank
    private String name;

    // 产权方编号
    @NotBlank
    private String ownerId;

    // 产品大类
    private String parentCategoryCode;

    // 产品分类
    @NotBlank
    private String categoryCode;

    // 列表图片
    @NotBlank
    private String listPic;

    // 详情banner图
    @NotBlank
    private String bannerPic;

    // 图文详情
    @NotBlank
    private String description;

    // 产地
    @NotBlank
    private String originPlace;

    // 学名
    @NotBlank
    private String scientificName;

    // 品种
    @NotBlank
    private String variety;

    // 级别
    @NotBlank
    private String rank;

    // 省
    @NotBlank
    private String province;

    // 市
    @NotBlank
    private String city;

    // 区
    @NotBlank
    private String area;

    // 镇
    @NotBlank
    private String town;

    // 经度
    @NotBlank
    private String longitude;

    // 维度
    @NotBlank
    private String latitude;

    // 单颗树产量
    @NotBlank
    private String singleOutput;

    // 包装单位
    @NotBlank
    private String packUnit;

    // 认养开始时间
    @NotBlank
    private String adoptStartDatetime;

    // 认养结束时间
    @NotBlank
    private String adoptEndDatetime;

    // 收货时间
    @NotBlank
    private String harvestDatetime;

    // 发货时间
    @NotBlank
    private String deliverDatetime;

    // 更新人
    @NotBlank
    private String updater;

    // 备注
    private String remark;

    // 规格列表
    @NotEmpty
    private List<XN629400ReqSpecs> presellSpecsList;

    // 树木列表
    @NotEmpty
    private List<XN629400ReqTree> treeList;

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

    public String getSingleOutput() {
        return singleOutput;
    }

    public void setSingleOutput(String singleOutput) {
        this.singleOutput = singleOutput;
    }

    public String getPackUnit() {
        return packUnit;
    }

    public void setPackUnit(String packUnit) {
        this.packUnit = packUnit;
    }

    public String getAdoptStartDatetime() {
        return adoptStartDatetime;
    }

    public void setAdoptStartDatetime(String adoptStartDatetime) {
        this.adoptStartDatetime = adoptStartDatetime;
    }

    public String getAdoptEndDatetime() {
        return adoptEndDatetime;
    }

    public void setAdoptEndDatetime(String adoptEndDatetime) {
        this.adoptEndDatetime = adoptEndDatetime;
    }

    public String getHarvestDatetime() {
        return harvestDatetime;
    }

    public void setHarvestDatetime(String harvestDatetime) {
        this.harvestDatetime = harvestDatetime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<XN629400ReqSpecs> getPresellSpecsList() {
        return presellSpecsList;
    }

    public void setPresellSpecsList(List<XN629400ReqSpecs> presellSpecsList) {
        this.presellSpecsList = presellSpecsList;
    }

    public List<XN629400ReqTree> getTreeList() {
        return treeList;
    }

    public void setTreeList(List<XN629400ReqTree> treeList) {
        this.treeList = treeList;
    }

    public String getParentCategoryCode() {
        return parentCategoryCode;
    }

    public void setParentCategoryCode(String parentCategoryCode) {
        this.parentCategoryCode = parentCategoryCode;
    }

    public String getDeliverDatetime() {
        return deliverDatetime;
    }

    public void setDeliverDatetime(String deliverDatetime) {
        this.deliverDatetime = deliverDatetime;
    }

}
