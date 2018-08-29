package com.ogc.standard.huobipro;

/**
 * 
 * @author: lei 
 * @since: 2018年8月25日 下午11:22:56 
 * @history:
 */
public class HBBalance {

    // 币种
    private String currency;

    // 类型 trade: 交易余额，frozen: 冻结余额
    private String type;

    // 余额
    private String balance;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

}
