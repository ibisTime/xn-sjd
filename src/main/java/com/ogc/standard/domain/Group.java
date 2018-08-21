package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 组合
* @author: lei
* @since: 2018年08月20日 下午08:14:48
* @history:
*/
public class Group extends ABaseDO {

    private static final long serialVersionUID = -7291507718340676621L;

    // 编号
    private String code;

    // 赛事编号
    private String matchCode;

    // 战队编号
    private String teamCode;

    // 用户编号
    private String userId;

    // 代币符号
    private String symbol;

    // 初始金额
    private BigDecimal initAmount;

    // 余额
    private BigDecimal balance;

    // 总资产
    private BigDecimal totalAssets;

    // 总收益
    private BigDecimal totalBenefit;

    // 今日收益
    private BigDecimal dayBenefit;

    // 周收益
    private BigDecimal weekBenefit;

    // 月收益
    private BigDecimal monthBenefit;

    // 排行
    private Integer orderNo;

    // 关注人数
    private Integer followNumber;

    // 状态(1-进行中，0-已结束)
    private String status;

    // 创建时间
    private Date createDatetime;

    // ******************db properties**************

    // 是否关注
    private String attentionFlag;

    // 是否提醒
    private String remindFlag;

    // 币种配置
    private List<GroupCoin> coinList;

    // 最新成交记录
    // private List<?> latestOrderDetailList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMatchCode() {
        return matchCode;
    }

    public void setMatchCode(String matchCode) {
        this.matchCode = matchCode;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
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

    public BigDecimal getInitAmount() {
        return initAmount;
    }

    public void setInitAmount(BigDecimal initAmount) {
        this.initAmount = initAmount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(BigDecimal totalAssets) {
        this.totalAssets = totalAssets;
    }

    public BigDecimal getTotalBenefit() {
        return totalBenefit;
    }

    public void setTotalBenefit(BigDecimal totalBenefit) {
        this.totalBenefit = totalBenefit;
    }

    public BigDecimal getDayBenefit() {
        return dayBenefit;
    }

    public void setDayBenefit(BigDecimal dayBenefit) {
        this.dayBenefit = dayBenefit;
    }

    public BigDecimal getWeekBenefit() {
        return weekBenefit;
    }

    public void setWeekBenefit(BigDecimal weekBenefit) {
        this.weekBenefit = weekBenefit;
    }

    public BigDecimal getMonthBenefit() {
        return monthBenefit;
    }

    public void setMonthBenefit(BigDecimal monthBenefit) {
        this.monthBenefit = monthBenefit;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getFollowNumber() {
        return followNumber;
    }

    public void setFollowNumber(Integer followNumber) {
        this.followNumber = followNumber;
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

    public String getAttentionFlag() {
        return attentionFlag;
    }

    public void setAttentionFlag(String attentionFlag) {
        this.attentionFlag = attentionFlag;
    }

    public String getRemindFlag() {
        return remindFlag;
    }

    public void setRemindFlag(String remindFlag) {
        this.remindFlag = remindFlag;
    }

    public List<GroupCoin> getCoinList() {
        return coinList;
    }

    public void setCoinList(List<GroupCoin> coinList) {
        this.coinList = coinList;
    }

}
