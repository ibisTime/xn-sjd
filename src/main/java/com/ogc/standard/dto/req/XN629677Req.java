package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 查询区域公章
 * @author: silver 
 * @since: Jan 25, 2019 4:37:10 PM 
 * @history:
 */
public class XN629677Req extends AListReq {

    private static final long serialVersionUID = -6308551102694787370L;

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

}
