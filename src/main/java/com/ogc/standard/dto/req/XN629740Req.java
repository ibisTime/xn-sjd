/**
 * @Title XN629740Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年11月7日 下午2:28:27 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: taojian 
 * @since: 2018年11月7日 下午2:28:27 
 * @history:
 */
public class XN629740Req {

    @NotBlank
    private String shopCode;

    @NotBlank
    private String deliverPlace;

    @NotBlank
    private String receivePlace;

    @NotBlank
    private String price;

    @NotBlank
    private String updater;

    private String remark;

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public String getDeliverPlace() {
        return deliverPlace;
    }

    public void setDeliverPlace(String deliverPlace) {
        this.deliverPlace = deliverPlace;
    }

    public String getReceivePlace() {
        return receivePlace;
    }

    public void setReceivePlace(String receivePlace) {
        this.receivePlace = receivePlace;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
