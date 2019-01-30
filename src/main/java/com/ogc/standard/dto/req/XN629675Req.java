package com.ogc.standard.dto.req;

/**
 * 分页查公章
 * @author: silver 
 * @since: Jan 17, 2019 4:01:02 PM 
 * @history:
 */
public class XN629675Req extends APageReq {

    private static final long serialVersionUID = -6308551102694787370L;

    // 省
    private String province;

    // 市
    private String city;

    // 区
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
