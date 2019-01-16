/**
 * @Title XN625000Res.java 
 * @Package com.cdkj.coin.dto.res 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月16日 下午5:02:42 
 * @version V1.0   
 */
package com.ogc.standard.dto.res;

/**
 * 查询产品区域
 * @author: silver 
 * @since: Jan 15, 2019 3:15:50 PM 
 * @history:
 */
public class XN629028Res {

    // 省
    private String province;

    // 市
    private String city;

    // 区
    private String area;

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

    public XN629028Res(String province, String city, String area) {
        super();
        this.province = province;
        this.city = city;
        this.area = area;
    }

}
