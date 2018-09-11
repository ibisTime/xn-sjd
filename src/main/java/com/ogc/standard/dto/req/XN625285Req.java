package com.ogc.standard.dto.req;

/** 
 * 分页查询承兑订单
 * @author: lei 
 * @since: 2018年9月10日 下午8:07:48 
 * @history:
 */
public class XN625285Req extends APageReq {

    private static final long serialVersionUID = -2480706203956407908L;

    // 选填，买方
    private String buyUser;

    // 选填，付款卡号
    private String payCardNo;

    // 选填，付款方式
    private String payType;

    // 选填，收款卡号
    private String receiveCardNo;

    // 选填，收款方式
    private String receiveType;

    // 选填，卖方
    private String sellUser;

    // 选填，状态
    private String status;

    // 选填，交易数字币种
    private String tradeCoin;

    // 选填，交易币种
    private String tradeCurrency;

    // 选填，类型
    private String type;

    public String getBuyUser() {
        return buyUser;
    }

    public void setBuyUser(String buyUser) {
        this.buyUser = buyUser;
    }

    public String getPayCardNo() {
        return payCardNo;
    }

    public void setPayCardNo(String payCardNo) {
        this.payCardNo = payCardNo;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getReceiveCardNo() {
        return receiveCardNo;
    }

    public void setReceiveCardNo(String receiveCardNo) {
        this.receiveCardNo = receiveCardNo;
    }

    public String getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(String receiveType) {
        this.receiveType = receiveType;
    }

    public String getSellUser() {
        return sellUser;
    }

    public void setSellUser(String sellUser) {
        this.sellUser = sellUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTradeCoin() {
        return tradeCoin;
    }

    public void setTradeCoin(String tradeCoin) {
        this.tradeCoin = tradeCoin;
    }

    public String getTradeCurrency() {
        return tradeCurrency;
    }

    public void setTradeCurrency(String tradeCurrency) {
        this.tradeCurrency = tradeCurrency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
