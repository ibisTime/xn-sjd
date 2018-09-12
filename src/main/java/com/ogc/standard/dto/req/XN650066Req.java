package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 
 * @author: lei 
 * @since: 2018年8月29日 下午3:52:07 
 * @history:
 */
public class XN650066Req {

    // 必填，交易币种
    @NotBlank
    private String symbol;

    // 必填，计价币种
    @NotBlank
    private String toSymbol;

    // 必填，K线类型
    @NotBlank
    private String period;

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

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

}
