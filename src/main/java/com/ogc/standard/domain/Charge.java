package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ogc.standard.dao.base.ABaseDO;

//充值（2步骤）
public class Charge extends ABaseDO {

    private static final long serialVersionUID = 340253581181024692L;

    // 充值编号
    private String code;

    // 账户编号
    private String accountNumber;

    // 类别（C端账号/B端账号/P平台账号）
    private String accountType;

    // 充值金额
    private transient BigDecimal amount;

    // 币种
    private String currency;

    // 业务类型编号（因为什么业务类型而充值）
    private String bizType;

    // 业务类型说明（因为什么业务类型而充值）
    private String bizNote;

    // 关联业务单号
    private String bizNo;

    // 支付渠道账号信息（如开户支行）
    private String payCardInfo;

    // 支付渠道账号（如银行卡号）
    private String payCardNo;

    // 状态（0 待审核/1 审核通过/2 审核不通过）
    private String status;

    // 申请人
    private String applyUser;

    // 申请时间
    private Date applyDatetime;

    // 支付回录人
    private String payUser;

    // 支付渠道的说明
    private String payNote;

    // 支付时间
    private Date payDatetime;

    // 支付渠道
    private String channelType;

    // *******************************

    // 订单编号模糊查询
    private String codeForQuery;

    // 申请时间起
    private Date applyDatetimeStart;

    // 申请时间止
    private Date applyDatetimeEnd;

    // 支付时间起
    private Date payDatetimeStart;

    // 支付时间止
    private Date payDatetimeEnd;

    // 币种列表
    private List<String> currencyList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public String getPayCardInfo() {
        return payCardInfo;
    }

    public void setPayCardInfo(String payCardInfo) {
        this.payCardInfo = payCardInfo;
    }

    public String getPayCardNo() {
        return payCardNo;
    }

    public void setPayCardNo(String payCardNo) {
        this.payCardNo = payCardNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public Date getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(Date applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    public String getPayUser() {
        return payUser;
    }

    public void setPayUser(String payUser) {
        this.payUser = payUser;
    }

    public String getPayNote() {
        return payNote;
    }

    public void setPayNote(String payNote) {
        this.payNote = payNote;
    }

    public Date getPayDatetime() {
        return payDatetime;
    }

    public void setPayDatetime(Date payDatetime) {
        this.payDatetime = payDatetime;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getCodeForQuery() {
        return codeForQuery;
    }

    public void setCodeForQuery(String codeForQuery) {
        this.codeForQuery = codeForQuery;
    }

    public Date getApplyDatetimeStart() {
        return applyDatetimeStart;
    }

    public void setApplyDatetimeStart(Date applyDatetimeStart) {
        this.applyDatetimeStart = applyDatetimeStart;
    }

    public Date getApplyDatetimeEnd() {
        return applyDatetimeEnd;
    }

    public void setApplyDatetimeEnd(Date applyDatetimeEnd) {
        this.applyDatetimeEnd = applyDatetimeEnd;
    }

    public Date getPayDatetimeStart() {
        return payDatetimeStart;
    }

    public void setPayDatetimeStart(Date payDatetimeStart) {
        this.payDatetimeStart = payDatetimeStart;
    }

    public Date getPayDatetimeEnd() {
        return payDatetimeEnd;
    }

    public void setPayDatetimeEnd(Date payDatetimeEnd) {
        this.payDatetimeEnd = payDatetimeEnd;
    }

    public List<String> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<String> currencyList) {
        this.currencyList = currencyList;
    }

}
