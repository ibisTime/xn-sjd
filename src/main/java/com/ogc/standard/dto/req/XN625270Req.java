package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 购买X币
 * @author: taojian 
 * @since: 2018年9月15日 下午3:25:34 
 * @history:
 */
public class XN625270Req {

    // 必填,收款方式
    @NotBlank
    private String receiveType;

    // 必填,付款金额
    @NotBlank
    private String tradeAmount;

    // 购买数量
    @NotBlank
    private String count;

    // 必填,交易币种
    @NotBlank
    private String tradeCurrency;

    // 必填,交易价格，当时行情价
    @NotBlank
    private String tradePrice;

    // 必填,购买用户编号
    @NotBlank
    private String userId;

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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setTradePrice(String tradePrice) {
        this.tradePrice = tradePrice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
