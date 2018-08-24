package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 【模拟交易】委托下单
 * @author: lei 
 * @since: 2018年8月22日 下午2:04:04 
 * @history:
 */
public class XN650050Req {

    // 必填，用户编号
    @NotBlank
    private String userId;

    // 必填，组合编号
    @NotBlank
    private String groupCode;

    // 必填，交易所
    @NotBlank
    private String exchange;

    // 必填，交易币种
    @NotBlank
    private String symbol;

    // 必填，计价币种
    @NotBlank
    private String toSymbol;

    // 必填，委托类型，0=市价，1=限价
    @NotBlank
    private String type;

    // 必填，委托买卖方向，0=买入，1=卖出
    @NotBlank
    private String direction;

    // 特殊必填，type=1时必填，委托价格
    private String price;

    // 必填，委托数量
    @NotBlank
    private String totalCount;

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

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getToSymbol() {
        return toSymbol;
    }

    public void setToSymbol(String toSymbol) {
        this.toSymbol = toSymbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }
}
