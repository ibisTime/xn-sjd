package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 挂单寄售
 * @author: silver 
 * @since: Nov 4, 2018 2:57:58 PM 
 * @history:
 */
public class XN629432Req {

    // 编号
    @NotBlank
    private String code;

    // 价格
    @NotBlank
    private String price;

    // 数量
    @NotBlank
    private String quantity;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}
