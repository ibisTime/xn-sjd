/**
 * @Title XN808071Res.java 
 * @Package com.xnjr.mall.dto.res 
 * @Description 
 * @author xieyj  
 * @date 2017年12月16日 下午4:23:27 
 * @version V1.0   
 */
package com.ogc.standard.dto.res;

import java.util.List;

import com.ogc.standard.domain.Cart;

/**
 * 我的购物车
 * @author: silver 
 * @since: Nov 15, 2018 11:42:35 AM 
 * @history:
 */
public class XN629712Res {
    // 店铺编号
    private String shopCode;

    // 店铺名称
    private String shopName;

    // 购物车列表
    private List<Cart> cartList;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public XN629712Res(String shopCode, String shopName, List<Cart> cartList) {
        super();
        this.shopCode = shopCode;
        this.shopName = shopName;
        this.cartList = cartList;
    }

}
