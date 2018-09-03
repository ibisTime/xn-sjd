package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by tianlei on 2017/十一月/23.
 */
public class XN625256Req {

    @NotBlank
    private String master;

    private String visitor;

    private String currency;

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
