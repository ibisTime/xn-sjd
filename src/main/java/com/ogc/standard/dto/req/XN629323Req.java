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

    // 收货人
    private String receiver;

    // 收货地址
    private String reAddress;

    // 收货人手机号
    private String reMobile;

    // 认领人
    @NotBlank
    private String claimer;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReAddress() {
        return reAddress;
    }

    public void setReAddress(String reAddress) {
        this.reAddress = reAddress;
    }

    public String getReMobile() {
        return reMobile;
    }

    public void setReMobile(String reMobile) {
        this.reMobile = reMobile;
    }

    public String getClaimer() {
        return claimer;
    }

    public void setClaimer(String claimer) {
        this.claimer = claimer;
    }

}
