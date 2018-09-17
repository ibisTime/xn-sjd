/**
 * @Title Award.java 
 * @Package com.ogc.standard.domain 
 * @Description 
 * @author taojian  
 * @date 2018年9月14日 下午4:01:23 
 * @version V1.0   
 */
package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/** 
 * @author: taojian 
 * @since: 2018年9月14日 下午4:01:23 
 * @history:
 */
public class Award extends ABaseDO {

    private static final long serialVersionUID = 5327123687230309391L;
    // ***********db properties***********

    // 编号
    private Long id;

    // 用户编号
    private String userId;

    // 用户类型
    private String userKind;

    // 币种（X币）
    private String currency;

    // 奖励
    private BigDecimal count;

    // 订单金额
    private BigDecimal orderCount;

    // 奖励比例
    private BigDecimal rate;

    // 参考类型(1=注册分佣 2=交易分佣)
    private String refType;

    // 参考订单编号
    private String refCode;

    // 参考说明
    private String refNote;

    // 状态(0=待结算 1=已结算 2=不结算）
    private String status;

    // 创建时间
    private Date createDatetime;

    // 处理时间
    private Date handleDatetime;

    // 备注
    private String remark;

    // ***********db properties***********
    private Date createDatetimeStart;

    private Date createDatetimeEnd;

    private User user;

    private User relUser;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getRelUser() {
        return relUser;
    }

    public void setRelUser(User relUser) {
        this.relUser = relUser;
    }

    public BigDecimal getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(BigDecimal orderCount) {
        this.orderCount = orderCount;
    }

    public Date getCreateDatetimeStart() {
        return createDatetimeStart;
    }

    public void setCreateDatetimeStart(Date createDatetimeStart) {
        this.createDatetimeStart = createDatetimeStart;
    }

    public Date getCreateDatetimeEnd() {
        return createDatetimeEnd;
    }

    public void setCreateDatetimeEnd(Date createDatetimeEnd) {
        this.createDatetimeEnd = createDatetimeEnd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserKind() {
        return userKind;
    }

    public void setUserKind(String userKind) {
        this.userKind = userKind;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    public String getRefNote() {
        return refNote;
    }

    public void setRefNote(String refNote) {
        this.refNote = refNote;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getHandleDatetime() {
        return handleDatetime;
    }

    public void setHandleDatetime(Date handleDatetime) {
        this.handleDatetime = handleDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
