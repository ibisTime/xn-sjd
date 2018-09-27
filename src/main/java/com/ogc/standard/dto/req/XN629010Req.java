package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 新增产品
 * @author: silver 
 * @since: 2018年9月27日 上午9:51:25 
 * @history:
 */
public class XN629010Req extends BaseReq {
    private static final long serialVersionUID = -6875342580769258538L;

    // 名称
    @NotBlank
    private String name;

    // 销售分类（0个人/1定向/2捐赠/3集体）
    @NotBlank
    private String sellType;

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

    // 募集开始时间
    private String raiseStartDatetime;

    // 募集结束时间
    private String raiseEndDatetime;

    // 募集总量
    private String raiseCount;

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

}
