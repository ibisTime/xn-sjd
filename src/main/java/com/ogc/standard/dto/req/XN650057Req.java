package com.ogc.standard.dto.req;

/** 
 * 
 * @author: lei 
 * @since: 2018年8月22日 下午3:38:40 
 * @history:
 */
public class XN650057Req extends APageReq {

    private static final long serialVersionUID = 1008764902858165436L;

    // 选填，委托单编号
    private String orderCode;

    // 选填，用户编号
    private String userId;

    // 选填，交易币种
    private String symbol;

    // 选填，计价币种
    private String toSymbol;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

}
