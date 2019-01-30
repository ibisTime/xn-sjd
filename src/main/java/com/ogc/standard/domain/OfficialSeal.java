package com.ogc.standard.domain;

import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 公章
* @author: silver 
* @since: 2019-01-25 14:59:20
* @history:
*/
public class OfficialSeal extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 省
    private String province;

    // 市
    private String city;

    // 区
    private String area;

    // 部门
    private String department;

    // 公章图片
    private String pic;

    // 更新人
    private String updater;

    // 更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
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

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

}
