package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 
 * @author: lei 
 * @since: 2018年8月22日 下午3:42:51 
 * @history:
 */
public class XN650058Req extends APageReq {

    private static final long serialVersionUID = 1008764902858165436L;

    // 必填，用户编号
    @NotBlank
    private String userId;

    // 选填，交易所
    private String exchange;

    // 选填，交易币种
    private String symbol;

    // 选填，计价币种
    private String toSymbol;

    // 选填，委托类型（0=市价，1=限价）
    private String type;

    // 选填，买卖方向（0=买入，1=卖出）
    private String direction;

    // 选填，状态（0=已提交，1=部分成交，2=部分成交撤销，3=完全成交，4=已撤销）
    private String status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
