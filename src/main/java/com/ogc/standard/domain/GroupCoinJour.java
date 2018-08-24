package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 币种配置账户流水
* @author: lei
* @since: 2018年08月23日 下午09:32:06
* @history:
*/
public class GroupCoinJour extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 订单分组组号
    private String payGroup;

    // 参考订单号
    private String refNo;

    // 账户编号
    private String accountNumber;

    // 变动金额
    private BigDecimal transAmount;

    // 用户编号
    private String userId;

    // 币种
    private String symbol;

    // 业务类型
    private String bizType;

    // 业务类型
    private String bizNote;

    // 变动前金额
    private BigDecimal preAmount;

    // 变动后金额
    private BigDecimal postAmount;

    // 创建时间
    private Date createDatetime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPayGroup() {
        return payGroup;
    }

    public void setPayGroup(String payGroup) {
        this.payGroup = payGroup;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(BigDecimal transAmount) {
        this.transAmount = transAmount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getBizNote() {
        return bizNote;
    }

    public void setBizNote(String bizNote) {
        this.bizNote = bizNote;
    }

    public BigDecimal getPreAmount() {
        return preAmount;
    }

    public void setPreAmount(BigDecimal preAmount) {
        this.preAmount = preAmount;
    }

    public BigDecimal getPostAmount() {
        return postAmount;
    }

    public void setPostAmount(BigDecimal postAmount) {
        this.postAmount = postAmount;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

}
