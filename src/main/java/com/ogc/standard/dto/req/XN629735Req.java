/**
 * @Title XN629730Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年11月7日 上午11:25:23 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

/** 
 * @author: taojian 
 * @since: 2018年11月7日 上午11:25:23 
 * @history:
 */
public class XN629735Req extends APageReq {

    private static final long serialVersionUID = -4386212963173934436L;

    private String orderCode;

    private String shopCode;

    private String commodityCode;

    private String specsId;

    private String status;

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

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String getSpecsId() {
        return specsId;
    }

    public void setSpecsId(String specsId) {
        this.specsId = specsId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
