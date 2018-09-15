package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 
 * @author: lei 
 * @since: 2018年9月15日 下午4:35:07 
 * @history:
 */
public class XN802008Req {

    @NotBlank
    private String symbol;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
