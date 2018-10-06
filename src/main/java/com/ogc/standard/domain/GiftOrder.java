package com.ogc.standard.domain;

import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 礼物订单
* @author: jiafr 
* @since: 2018-09-30 10:47:35
* @history:
*/
public class GiftOrder extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 认养权编号
    private String adoptTreeCode;

    // 礼物名称
    private String name;

    // 礼物价格
    private Long price;

    // 礼物描述
    private String description;

    // 收货人
    private String receiver;

    // 收货地址
    private String reAddress;

    // 收货人手机号
    private String reMobile;

    // 状态（0待认领/1已认领）
    private String status;

    // 产生时间
    private Date createDatetime;

    // 认领人
    private String claimer;

    // 认领时间
    private Date claimDatetime;

    // 失效时间
    private Date invalidDatetime;

    /************DB properties*********/

    private Date claimDatetimeStart;// 认领时间起

    private Date claimDatetimeEnd;// 认领时间止

    // 失效开始时间
    private Date invalidStartDatetime;

    // 失效结束时间
    private Date invalidEndDatetime;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setAdoptTreeCode(String adoptTreeCode) {
        this.adoptTreeCode = adoptTreeCode;
    }

    public String getAdoptTreeCode() {
        return adoptTreeCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReAddress(String reAddress) {
        this.reAddress = reAddress;
    }

    public String getReAddress() {
        return reAddress;
    }

    public void setReMobile(String reMobile) {
        this.reMobile = reMobile;
    }

    public String getReMobile() {
        return reMobile;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public void setClaimer(String claimer) {
        this.claimer = claimer;
    }

    public String getClaimer() {
        return claimer;
    }

    public Date getClaimDatetime() {
        return claimDatetime;
    }

    public void setClaimDatetime(Date claimDatetime) {
        this.claimDatetime = claimDatetime;
    }

    public Date getInvalidDatetime() {
        return invalidDatetime;
    }

    public void setInvalidDatetime(Date invalidDatetime) {
        this.invalidDatetime = invalidDatetime;
    }

    public Date getClaimDatetimeStart() {
        return claimDatetimeStart;
    }

    public void setClaimDatetimeStart(Date claimDatetimeStart) {
        this.claimDatetimeStart = claimDatetimeStart;
    }

    public Date getClaimDatetimeEnd() {
        return claimDatetimeEnd;
    }

    public void setClaimDatetimeEnd(Date claimDatetimeEnd) {
        this.claimDatetimeEnd = claimDatetimeEnd;
    }

    public Date getInvalidStartDatetime() {
        return invalidStartDatetime;
    }

    public void setInvalidStartDatetime(Date invalidStartDatetime) {
        this.invalidStartDatetime = invalidStartDatetime;
    }

    public Date getInvalidEndDatetime() {
        return invalidEndDatetime;
    }

    public void setInvalidEndDatetime(Date invalidEndDatetime) {
        this.invalidEndDatetime = invalidEndDatetime;
    }

}
