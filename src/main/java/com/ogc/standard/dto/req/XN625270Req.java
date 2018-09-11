package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 购买X币
 * @author: lei 
 * @since: 2018年9月10日 下午8:07:48 
 * @history:
 */
public class XN625270Req {

    // 必填,付款卡号
    @NotBlank
    private String payCardNo;

    // 条件必填,银行卡号
    private String payBank;

    // 必填,付款方式
    @NotBlank
    private String payType;

    // 必填,付款金额
    @NotBlank
    private String tradeAmount;

    // 必填,交易币种
    @NotBlank
    private String tradeCurrency;

    // 必填,交易价格，当时行情价
    @NotBlank
    private String tradePrice;

    // 必填,交易数量，当时行情价
    @NotBlank
    private String count;

    // 必填,购买用户编号
    @NotBlank
    private String userId;

    public String getPayCardNo() {
        return payCardNo;
    }

    public void setPayCardNo(String payCardNo) {
        this.payCardNo = payCardNo;
    }

    public String getPayBank() {
        return payBank;
    }

    public void setPayBank(String payBank) {
        this.payBank = payBank;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
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
