package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 分红明细
* @author: lei
* @since: 2018年09月15日 上午11:35:42
* @history:
*/
public class DivideDetail extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String id;

    // 用户编号
    private String userId;

    // 币种(X币)
    private String currency;

    // 当时余额
    private BigDecimal amount;

    // 分红金额
    private BigDecimal divideAmount;

    // 创建时间
    private Date createDatetime;

    // 分红时间
    private Date divideDatetime;

    // 分红ID
    private String divideId;

    // ******************db properties**************

    // 用户详情
    private User userInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getDivideAmount() {
        return divideAmount;
    }

    public void setDivideAmount(BigDecimal divideAmount) {
        this.divideAmount = divideAmount;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getDivideDatetime() {
        return divideDatetime;
    }

    public void setDivideDatetime(Date divideDatetime) {
        this.divideDatetime = divideDatetime;
    }

    public String getDivideId() {
        return divideId;
    }

    public void setDivideId(String divideId) {
        this.divideId = divideId;
    }

    public User getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }

}
