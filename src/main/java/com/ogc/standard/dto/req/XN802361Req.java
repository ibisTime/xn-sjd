package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 手动设置eth token归集
 * @author: 13912 
 * @since: 2018年9月3日 下午2:07:25 
 * @history:
 */
public class XN802361Req {

    @NotBlank(message = "请先设置eth token归集阀值")
    private String balanceStart; // eth token归集阀值

    @NotBlank(message = "请先设置币种符号")
    private String symbol;

    public String getBalanceStart() {
        return balanceStart;
    }

    public void setBalanceStart(String balanceStart) {
        this.balanceStart = balanceStart;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
