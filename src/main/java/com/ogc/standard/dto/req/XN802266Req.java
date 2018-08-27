/**
 * @Title XN802250Req.java 
 * @Package com.cdkj.coin.wallet.dto.req 
 * @Description 
 * @author haiqingzheng  
 * @date 2018年3月13日 上午11:13:30 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: haiqingzheng 
 * @since: 2018年3月13日 上午11:13:30 
 * @history:
 */
public class XN802266Req {

    @NotBlank
    private String symbol;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
