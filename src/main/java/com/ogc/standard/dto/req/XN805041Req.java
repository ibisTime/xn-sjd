package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class XN805041Req {

    @NotBlank
    private String mobile; // 手机号

    @Length(min = 2, max = 10, message = "昵称长度必须在2-10之间")
    private String nickname; // 昵称

    @NotBlank
    private String loginPwd; // 登录密码

    private String userRefereeType;// 推荐人类型

    private String userReferee; // 推荐人手机号

    @NotBlank
    private String smsCaptcha; // 手机验证码

    // 所属代理商
    private String agent;

    // 所属业务员
    private String salesman;

    private String longitude;// 经度

    private String latitude;// 纬度

    private String province; // 省(选填)

    private String city; // 市(选填)

    private String area; // 区(选填)

    public String getUserRefereeType() {
        return userRefereeType;
    }

    public void setUserRefereeType(String userRefereeType) {
        this.userRefereeType = userRefereeType;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getUserReferee() {
        return userReferee;
    }

    public void setUserReferee(String userReferee) {
        this.userReferee = userReferee;
    }

    public String getSmsCaptcha() {
        return smsCaptcha;
    }

    public void setSmsCaptcha(String smsCaptcha) {
        this.smsCaptcha = smsCaptcha;
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

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

}
