package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 卖出X币
 * @author: lei 
 * @since: 2018年9月10日 下午8:07:48 
 * @history:
 */
public class XN625271Req {

    // 必填，收款卡号
    @NotBlank
    private String receiveCardNo;

    // 必填，收款银行
    @NotBlank
    private String receiveBank;

    // 必填，收款方式
    @NotBlank
    private String receiveType;

    // 必填,出售金额
    @NotBlank
    private String tradeAmount;

    // 必填,交易币种
    @NotBlank
    private String tradeCurrency;

    // 必填,交易价格，当时行情价
    @NotBlank
    private String tradePrice;

    // 必填，出售数量
    @NotBlank
    private String count;

    // 必填,购买用户编号
    @NotBlank
    private String userId;

    public String getReceiveCardNo() {
        return receiveCardNo;
    }

    public void setReceiveCardNo(String receiveCardNo) {
        this.receiveCardNo = receiveCardNo;
    }

    public String getReceiveBank() {
        return receiveBank;
    }

    public void setReceiveBank(String receiveBank) {
        this.receiveBank = receiveBank;
    }

    public String getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(String receiveType) {
        this.receiveType = receiveType;
    }

    public String getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(String tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public String getTradeCurrency() {
        return tradeCurrency;
    }

    public void setTradeCurrency(String tradeCurrency) {
        this.tradeCurrency = tradeCurrency;
    }

    public String getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(String tradePrice) {
        this.tradePrice = tradePrice;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
