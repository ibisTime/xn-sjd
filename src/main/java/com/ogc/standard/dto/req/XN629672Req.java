package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 添加公章
 * @author: silver 
 * @since: Jan 25, 2019 4:12:55 PM 
 * @history:
 */
public class XN629672Req {

    // 编号
    @NotBlank
    private String code;

    // 省
    @NotBlank
    private String province;

    // 市
    @NotBlank
    private String city;

    // 区
    @NotBlank
    private String area;

    // 部门
    private String department;

    // 公章图片
    @NotBlank
    private String pic;

    // 更新人
    @NotBlank
    private String updater;

    // 备注
    private String remark;

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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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

}
