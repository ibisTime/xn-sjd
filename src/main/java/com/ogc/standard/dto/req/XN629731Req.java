/**
 * @Title XN629730Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年11月7日 上午11:25:23 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: taojian 
 * @since: 2018年11月7日 上午11:25:23 
 * @history:
 */
public class XN629731Req {

    @NotBlank
    private String code;

    @NotBlank
    private String shopCode;

    @NotBlank
    private String receiver;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

}
