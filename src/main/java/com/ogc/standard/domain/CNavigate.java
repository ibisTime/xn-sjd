package com.ogc.standard.domain;

import com.ogc.standard.dao.base.ABaseDO;

/**
 * 导航
 * @author: dl 
 * @since: 2018年8月18日 下午2:54:24 
 * @history:
 */
public class CNavigate extends ABaseDO {

    private static final long serialVersionUID = 7704492196281165271L;

    // ***********db properties***********
    // 编号
    private String code;

    // 店铺编号
    private String shopCode;

    // 名称
    private String name;

    // 类型
    private String type;

    // 访问Url
    private String url;

    // 图片
    private String pic;

    // 状态(1 启用 0 不启用)
    private String status;

    // 位置
    private String location;

    // 相对位置编号
    private Integer orderNo;

    // 父编号
    private String parentCode;

    // 备注
    private String remark;

    // ***********db properties***********
    // 是否公司修改
    private String isCompanyEdit;

    // 是否前端查询
    private String isFront;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsFront() {
        return isFront;
    }

    public void setIsFront(String isFront) {
        this.isFront = isFront;
    }

    public String getIsCompanyEdit() {
        return isCompanyEdit;
    }

    public void setIsCompanyEdit(String isCompanyEdit) {
        this.isCompanyEdit = isCompanyEdit;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

}
