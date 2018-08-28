/**
 * @Title XN802170Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2018年2月1日 下午8:09:43 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: haiqingzheng 
 * @since: 2018年2月1日 下午8:09:43 
 * @history:
 */
public class XN802010Req {

    // 提现地址
    @NotBlank
    private String address;

    // 标签
    @NotBlank
    private String label;

    // 是否设置为认证
    @NotBlank
    private String isCerti;

    // 用户编号
    @NotBlank
    private String userId;

    // 币种
    @NotBlank
    private String currency;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIsCerti() {
        return isCerti;
    }

    public void setIsCerti(String isCerti) {
        this.isCerti = isCerti;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
