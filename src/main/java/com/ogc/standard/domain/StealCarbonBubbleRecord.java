package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 偷取碳泡泡记录
* @author: silver 
* @since: 2019-01-16 15:23:20
* @history:
*/
public class StealCarbonBubbleRecord extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 偷取人编号
    private String stealUserId;

    // 被偷取人编号
    private String stealedUserId;

    // 认养权编号
    private String adoptTreeCode;

    // 碳泡泡订单编号
    private String carbonBubbleOrderCode;

    // 数量
    private BigDecimal quantity;

    // 偷取时间
    private Date stealDatetime;

    /********DB Properties***********/
    // 偷取开始时间
    private Date stealStartDatetime;

    // 偷取结束时间
    private Date stealEndDatetime;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setStealUserId(String stealUserId) {
        this.stealUserId = stealUserId;
    }

    public String getStealUserId() {
        return stealUserId;
    }

    public void setStealedUserId(String stealedUserId) {
        this.stealedUserId = stealedUserId;
    }

    public String getStealedUserId() {
        return stealedUserId;
    }

    public void setAdoptTreeCode(String adoptTreeCode) {
        this.adoptTreeCode = adoptTreeCode;
    }

    public String getAdoptTreeCode() {
        return adoptTreeCode;
    }

    public void setCarbonBubbleOrderCode(String carbonBubbleOrderCode) {
        this.carbonBubbleOrderCode = carbonBubbleOrderCode;
    }

    public String getCarbonBubbleOrderCode() {
        return carbonBubbleOrderCode;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Date getStealDatetime() {
        return stealDatetime;
    }

    public void setStealDatetime(Date stealDatetime) {
        this.stealDatetime = stealDatetime;
    }

    public Date getStealStartDatetime() {
        return stealStartDatetime;
    }

    public void setStealStartDatetime(Date stealStartDatetime) {
        this.stealStartDatetime = stealStartDatetime;
    }

    public Date getStealEndDatetime() {
        return stealEndDatetime;
    }

    public void setStealEndDatetime(Date stealEndDatetime) {
        this.stealEndDatetime = stealEndDatetime;
    }

}
