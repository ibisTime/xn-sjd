package com.ogc.standard.dto.req;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 修改产品
 * @author: silver 
 * @since: 2018年9月27日 上午9:51:25 
 * @history:
 */
public class XN629011Req extends BaseReq {
    private static final long serialVersionUID = -6875342580769258538L;

    // 编号
    @NotBlank
    private String code;

    // 名称
    @NotBlank
    private String name;

    // 销售分类（0个人/1定向/2捐赠/3集体）
    @NotBlank
    private String sellType;

    // 定向类型(1=等级 2=个人)
    private String directType;

    // 定向对象
    private String directObject;

    // 产权方编号
    @NotBlank
    private String ownerId;

    // 产品分类
    @NotBlank
    private String categoryCode;

    // 列表图片
    @NotBlank
    private String listPic;

    // 详情banner图
    @NotBlank
    private String bannerPic;

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

    // 描述
    @NotBlank
    private String description;

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

    // 募集开始时间(捐赠)
    private String raiseStartDatetime;

    // 募集结束时间(捐赠)
    private String raiseEndDatetime;

    // 认养开始时间(捐赠)
    private String startDatetime;

    // 认养结束时间(捐赠)
    private String endDatetime;

    // 募集总数量(集体)
    private String raiseCount;

    // 树龄
    private String age;

    // 最大积分抵扣比例
    private String maxJfdkRate;

    // 产品规格列表
    @NotEmpty
    private List<XN629010ReqSpecs> productSpecsList;

    // 树木列表
    @NotEmpty
    private List<XN629010ReqTree> treeList;

    // 更新人
    @NotBlank
    private String updater;

    // 备注
    private String remark;

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

    public String getRaiseStartDatetime() {
        return raiseStartDatetime;
    }

    public void setRaiseStartDatetime(String raiseStartDatetime) {
        this.raiseStartDatetime = raiseStartDatetime;
    }

    public String getRaiseEndDatetime() {
        return raiseEndDatetime;
    }

    public void setRaiseEndDatetime(String raiseEndDatetime) {
        this.raiseEndDatetime = raiseEndDatetime;
    }

    public String getRaiseCount() {
        return raiseCount;
    }

    public void setRaiseCount(String raiseCount) {
        this.raiseCount = raiseCount;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<XN629010ReqSpecs> getProductSpecsList() {
        return productSpecsList;
    }

    public void setProductSpecsList(List<XN629010ReqSpecs> productSpecsList) {
        this.productSpecsList = productSpecsList;
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

    public List<XN629010ReqTree> getTreeList() {
        return treeList;
    }

    public void setTreeList(List<XN629010ReqTree> treeList) {
        this.treeList = treeList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public String getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(String startDatetime) {
        this.startDatetime = startDatetime;
    }

    public String getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(String endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getMaxJfdkRate() {
        return maxJfdkRate;
    }

    public void setMaxJfdkRate(String maxJfdkRate) {
        this.maxJfdkRate = maxJfdkRate;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

}
