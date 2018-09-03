/**
 * @Title XN625250Req.java 
 * @Package com.cdkj.coin.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月14日 下午1:42:48 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import java.util.List;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月14日 下午1:42:48 
 * @history:
 */
public class XN625250Req extends APageReq {

    private static final long serialVersionUID = -8196427492618081494L;

    // 订单编号
    private String code;

    // 订单类型
    private String type;

    // 广告编号
    private String adsCode;

    // 买方
    private String buyUser;

    // 卖方
    private String sellUser;

    // 交易币种
    private String tradeCurrency;

    // 订单币种
    private String tradeCoin;

    // 付款方式
    private String payType;

    // 状态
    private String status;

    // 状态列表
    private List<String> statusList;

    private List<String> currencyList;

    // 所属用户
    private String belongUser;

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

    public String getAdsCode() {
        return adsCode;
    }

    public void setAdsCode(String adsCode) {
        this.adsCode = adsCode;
    }

    public String getBuyUser() {
        return buyUser;
    }

    public void setBuyUser(String buyUser) {
        this.buyUser = buyUser;
    }

    public String getSellUser() {
        return sellUser;
    }

    public void setSellUser(String sellUser) {
        this.sellUser = sellUser;
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

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getBelongUser() {
        return belongUser;
    }

    public void setBelongUser(String belongUser) {
        this.belongUser = belongUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<String> currencyList) {
        this.currencyList = currencyList;
    }

}
