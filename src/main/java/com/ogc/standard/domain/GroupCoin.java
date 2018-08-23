package com.ogc.standard.domain;

import java.math.BigDecimal;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 币种配置
* @author: lei
* @since: 2018年08月20日 下午10:16:16
* @history:
*/
public class GroupCoin extends ABaseDO {

    private static final long serialVersionUID = 8922342810194925778L;

    // 序号（自增长）
    private long id;

    // 组合编号
    private String groupCode;

    // 代币符号
    private String symbol;

    // 数量
    private BigDecimal count;

    // 币种资产
    private BigDecimal assets;

    // 占比
    private Double rate;

    // ******************db properties**************

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setAssets(BigDecimal assets) {
        this.assets = assets;
    }

    public BigDecimal getAssets() {
        return assets;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getRate() {
        return rate;
    }

}
