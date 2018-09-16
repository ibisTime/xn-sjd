package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by tianlei on 2017/十二月/05.
 */
public class XN625292Req {

    @NotBlank
    private String coin;

    @NotBlank(message = "对应币种缺少")
    private String refCurrency;

    public String getRefCurrency() {
        return refCurrency;
    }

    public void setRefCurrency(String refCurrency) {
        this.refCurrency = refCurrency;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }
}
