/**
 * @Title XN625000Res.java 
 * @Package com.cdkj.coin.dto.res 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月16日 下午5:02:42 
 * @version V1.0   
 */
package com.ogc.standard.dto.res;

import java.util.List;

import com.ogc.standard.domain.CommodityOrderDetail;
import com.ogc.standard.domain.Company;

/**
 * 根据支付组号查订单明细
 * @author: silver 
 * @since: Dec 5, 2018 3:04:38 PM 
 * @history:
 */
public class XN629802Res {
    private Company shop;

    private List<CommodityOrderDetail> commodityOrderDetails;

    public Company getShop() {
        return shop;
    }

    public void setShop(Company shop) {
        this.shop = shop;
    }

    public List<CommodityOrderDetail> getCommodityOrderDetails() {
        return commodityOrderDetails;
    }

    public void setCommodityOrderDetails(
            List<CommodityOrderDetail> commodityOrderDetails) {
        this.commodityOrderDetails = commodityOrderDetails;
    }

    public XN629802Res(Company shop,
            List<CommodityOrderDetail> commodityOrderDetails) {
        super();
        this.shop = shop;
        this.commodityOrderDetails = commodityOrderDetails;
    }

}
