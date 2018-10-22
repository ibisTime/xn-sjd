package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 认领礼物
 * @author: jiafr 
 * @since: 2018年9月30日 下午1:48:21 
 * @history:
 */
public class XN629323Req {

    // 编号
    @NotBlank
    private String code;

    // 收货地址编号
    @NotBlank
    private String addressCode;

    // 认领人
    @NotBlank
    private String claimer;

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

    public String getClaimer() {
        return claimer;
    }

    public void setClaimer(String claimer) {
        this.claimer = claimer;
    }

}
