package com.ogc.standard.domain;

import java.math.BigDecimal;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 盘口
* @author: lei
* @since: 2018年08月29日 下午03:33:00
* @history:
*/
public class Handicap extends ABaseDO {

    private static final long serialVersionUID = -4662813372033047960L;

    // 编号(自增)
    private Long id;

    // 委托单
    private String orderCode;

    // 价格
    private BigDecimal price;

    // 数量
    private BigDecimal count;

    // 买卖方向
    private String direction;

    // 交易币种
    private String symbol;

    // 计价币种
    private String toSymbol;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setToSymbol(String toSymbol) {
        this.toSymbol = toSymbol;
    }

    public String getToSymbol() {
        return toSymbol;
    }

}
