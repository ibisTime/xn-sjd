package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

public class XN805042Req {
    // 登录名（必填）
    private String loginName;

    // 详细地址
    private String address;

    // 登录密码（选填）
    private String loginPwd;

    // 手机号（选填）
    @NotBlank
    private String mobile;

    // 证件类型（选填）
    private String idKind;

    // 证件号码（选填）
    private String idNo;

    // 真实姓名（选填）
    private String realName;

    // 推荐人（选填）
    private String userReferee;

    // 省(选填)
    private String province;

    // 市(选填)
    private String city;

    // 区(选填)
    private String area;

    // 经度(选填)
    private String longitude;

    // 维度(选填)
    private String latitude;

    // 更新人(必填)
    @NotBlank
    private String updater;

    // 备注(选填)
    private String remark;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdKind() {
        return idKind;
    }

    public void setIdKind(String idKind) {
        this.idKind = idKind;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserReferee() {
        return userReferee;
    }

    public void setUserReferee(String userReferee) {
        this.userReferee = userReferee;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
