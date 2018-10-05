package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 碳泡泡产生订单
* @author: jiafr 
* @since: 2018-10-02 13:49:28
* @history:
*/
public class CarbonBubbleOrder extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 认养权编号
    private String adoptTreeCode;

    // 用户编号
    private String adoptUserId;

    // 生成时间
    private Date createDatetime;

    // 过期时间
    private Date invalidDatetime;

    // 碳泡泡数量
    private BigDecimal quantity;

    // 状态（待收取、已收完、已过期）
    private String status;

    // 收取人
    private String taker;

    // 收取时间
    private Date takeDatetime;

    /************DB properties**********/

    /***********辅助字段*********/

    // 生成时间起
    private Date createDatetimeStart;

    // 生成时间止
    private Date createDatetimeEnd;

    // 收取时间
    private Date takeDatetimeStart;

    // 收取时间
    private Date takeDatetimeEnd;

    // 收取人用户信息
    private User takeUser;

    public String getAdoptUserId() {
        return adoptUserId;
    }

    public void setAdoptUserId(String adoptUserId) {
        this.adoptUserId = adoptUserId;
    }

    public User getTakeUser() {
        return takeUser;
    }

    public void setTakeUser(User takeUser) {
        this.takeUser = takeUser;
    }

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

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getInvalidDatetime() {
        return invalidDatetime;
    }

    public void setInvalidDatetime(Date invalidDatetime) {
        this.invalidDatetime = invalidDatetime;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Date getTakeDatetime() {
        return takeDatetime;
    }

    public void setTakeDatetime(Date takeDatetime) {
        this.takeDatetime = takeDatetime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setTaker(String taker) {
        this.taker = taker;
    }

    public String getTaker() {
        return taker;
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

    public Date getTakeDatetimeStart() {
        return takeDatetimeStart;
    }

    public void setTakeDatetimeStart(Date takeDatetimeStart) {
        this.takeDatetimeStart = takeDatetimeStart;
    }

    public Date getTakeDatetimeEnd() {
        return takeDatetimeEnd;
    }

    public void setTakeDatetimeEnd(Date takeDatetimeEnd) {
        this.takeDatetimeEnd = takeDatetimeEnd;
    }
}
