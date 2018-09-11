package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 承兑交易订单
* @author: lei
* @since: 2018年09月10日 下午04:34:54
* @history:
*/
public class CoinAcceptOrder extends ABaseDO {

    private static final long serialVersionUID = -543164987108114191L;

    // 编号
    private String code;

    // 类型(0买入/1卖出)
    private String type;

    // 用户编号
    private String userId;

    // 承兑商
    private String acceptUser;

    // 交易币种
    private String tradeCurrency;

    // 交易数字货币
    private String tradeCoin;

    // 交易单价
    private BigDecimal tradePrice;

    // 交易数量
    private BigDecimal count;

    // 交易总额
    private BigDecimal tradeAmount;

    // 手续费
    private BigDecimal fee;

    // 支付超时时间
    private Date invalidDatetime;

    // 付款方式
    private String payType;

    // 付款信息
    private String payInfo;

    // 付款银行
    private String payBank;

    // 付款卡号
    private String payCardNo;

    // 收款方式
    private String receiveType;

    // 收款信息
    private String receiveInfo;

    // 收款银行
    private String receiveBank;

    // 收款卡号
    private String receiveCardNo;

    // 状态(0=待支付 1=已支付 2=已释放 3=已取消)
    private String status;

    // 打款时间
    private Date markDatetime;

    // 打款说明
    private String markNote;

    // 币释放时间
    private Date releaseDatetime;

    // 创建时间
    private Date createDatetime;

    // 最后更新人
    private String updater;

    // 最后更新时间
    private Date updateDatetime;

    // 备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAcceptUser() {
        return acceptUser;
    }

    public void setAcceptUser(String acceptUser) {
        this.acceptUser = acceptUser;
    }

    public String getTradeCurrency() {
        return tradeCurrency;
    }

    public void setTradeCurrency(String tradeCurrency) {
        this.tradeCurrency = tradeCurrency;
    }

    public String getTradeCoin() {
        return tradeCoin;
    }

    public void setTradeCoin(String tradeCoin) {
        this.tradeCoin = tradeCoin;
    }

    public BigDecimal getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(BigDecimal tradePrice) {
        this.tradePrice = tradePrice;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Date getInvalidDatetime() {
        return invalidDatetime;
    }

    public void setInvalidDatetime(Date invalidDatetime) {
        this.invalidDatetime = invalidDatetime;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(String payInfo) {
        this.payInfo = payInfo;
    }

    public String getPayBank() {
        return payBank;
    }

    public void setPayBank(String payBank) {
        this.payBank = payBank;
    }

    public String getPayCardNo() {
        return payCardNo;
    }

    public void setPayCardNo(String payCardNo) {
        this.payCardNo = payCardNo;
    }

    public String getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(String receiveType) {
        this.receiveType = receiveType;
    }

    public String getReceiveInfo() {
        return receiveInfo;
    }

    public void setReceiveInfo(String receiveInfo) {
        this.receiveInfo = receiveInfo;
    }

    public String getReceiveBank() {
        return receiveBank;
    }

    public void setReceiveBank(String receiveBank) {
        this.receiveBank = receiveBank;
    }

    public String getReceiveCardNo() {
        return receiveCardNo;
    }

    public void setReceiveCardNo(String receiveCardNo) {
        this.receiveCardNo = receiveCardNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getMarkDatetime() {
        return markDatetime;
    }

    public void setMarkDatetime(Date markDatetime) {
        this.markDatetime = markDatetime;
    }

    public String getMarkNote() {
        return markNote;
    }

    public void setMarkNote(String markNote) {
        this.markNote = markNote;
    }

    public Date getReleaseDatetime() {
        return releaseDatetime;
    }

    public void setReleaseDatetime(Date releaseDatetime) {
        this.releaseDatetime = releaseDatetime;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
