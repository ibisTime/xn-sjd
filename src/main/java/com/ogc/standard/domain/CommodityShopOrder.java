/**
 * @Title CommodityShopOrder.java 
 * @Package com.ogc.standard.domain 
 * @Description 
 * @author taojian  
 * @date 2018年11月12日 上午10:17:17 
 * @version V1.0   
 */
package com.ogc.standard.domain;

import java.util.List;

import com.ogc.standard.dao.base.ABaseDO;

/** 
 * @author: taojian 
 * @since: 2018年11月12日 上午10:17:17 
 * @history:
 */
public class CommodityShopOrder extends ABaseDO {

    private static final long serialVersionUID = 4252262698860038523L;

    private String orderCode;

    private String shopCode;

    private String shopName;

    private List<CommodityOrderDetail> detailList;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<CommodityOrderDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<CommodityOrderDetail> detailList) {
        this.detailList = detailList;
    }
}
