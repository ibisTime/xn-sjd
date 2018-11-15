/**
 * @Title XN629713Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年11月10日 上午11:04:03 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 根据店铺删购物车
 * @author: silver 
 * @since: Nov 15, 2018 7:04:34 PM 
 * @history:
 */
public class XN629714Req {
    // 店铺编号
    @NotBlank
    private String shopCode;

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

}
