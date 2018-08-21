/**
 * @Title XN805081Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author dl  
 * @date 2018年8月21日 下午3:27:19 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 修改定位信息
 * @author: dl 
 * @since: 2018年8月21日 下午3:27:19 
 * @history:
 */
public class XN805081Req {

    // 地址
    private String adress;

    // 区
    @NotBlank
    private String area;

    // 市
    @NotBlank
    private String city;

    // 省
    @NotBlank
    private String province;

    // 经度
    private String latitude;

    // 纬度
    private String longitude;

    // 备注
    private String remark;

    // 更新人
    @NotBlank
    private String updater;

    // 用户编号
    @NotBlank
    private String userId;

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
