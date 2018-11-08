/**
 * @Title XN629740Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年11月7日 下午2:28:27 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;


/** 
 * @author: taojian 
 * @since: 2018年11月7日 下午2:28:27 
 * @history:
 */
public class XN629745Req extends APageReq {

    private static final long serialVersionUID = 7410180601823077289L;

    private String shopCode;

    private String deliverPlace;

    private String receivePlace;

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

}
