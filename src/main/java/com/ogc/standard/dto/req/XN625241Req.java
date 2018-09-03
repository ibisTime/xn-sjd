/**
 * @Title XN625240Req.java 
 * @Package com.cdkj.coin.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月14日 下午12:41:06 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 我要购买
 * @author: haiqingzheng 
 * @since: 2017年11月14日 下午12:41:06 
 * @history:
 */
public class XN625241Req {

    @NotBlank
    private String adsCode; // 广告编号

    @NotBlank
    private String sellUser; // 卖方

    @NotBlank
    private String tradePrice; // 交易单价

    @NotBlank
    private String count; // 交易数字货币数量

    @NotBlank
    private String tradeAmount; // 交易总额

    public String getAdsCode() {
        return adsCode;
    }

    public void setAdsCode(String adsCode) {
        this.adsCode = adsCode;
    }

    public String getSellUser() {
        return sellUser;
    }

    public void setSellUser(String sellUser) {
        this.sellUser = sellUser;
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

    public String getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(String tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

}
