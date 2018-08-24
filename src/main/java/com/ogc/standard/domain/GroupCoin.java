package com.ogc.standard.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 币种配置
* @author: lei
* @since: 2018年08月20日 下午10:16:16
* @history:
*/
public class GroupCoin extends ABaseDO {

    private static final long serialVersionUID = 8922342810194925778L;

    // 账户编号
    private String accountNumber;

    // 用户编号
    private String userId;

    // 组合编号
    private String groupCode;

    // 代币符号
    private String symbol;

    // 数量
    private BigDecimal count;

    // 冻结数量
    private BigDecimal frozenCount;

    // 币种资产
    private BigDecimal assets;

    // 占比
    private Double rate;

    // 最近一次变动对应的流水编号
    private String lastOrder;

    // 创建时间
    private Date createDatetime;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getFrozenCount() {
        return frozenCount;
    }

    public void setFrozenCount(BigDecimal frozenCount) {
        this.frozenCount = frozenCount;
    }

    public BigDecimal getAssets() {
        return assets;
    }

    public void setAssets(BigDecimal assets) {
        this.assets = assets;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getLastOrder() {
        return lastOrder;
    }

    public void setLastOrder(String lastOrder) {
        this.lastOrder = lastOrder;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

}
