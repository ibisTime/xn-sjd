package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改收货地址
 * @author: silver 
 * @since: Nov 21, 2018 3:26:57 PM 
 * @history:
 */
public class XN629800Req {
    // 编号
    @NotBlank
    private String code;

    // 地址编号
    @NotBlank
    private String addressCode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

}
