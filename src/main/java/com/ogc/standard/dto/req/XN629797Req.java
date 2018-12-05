package com.ogc.standard.dto.req;

/**
 * 列表查默认邮费
 * @author: silver 
 * @since: Dec 5, 2018 2:51:28 PM 
 * @history:
 */
public class XN629797Req extends AListReq {

    private static final long serialVersionUID = 8511651091614218636L;

    // 编号
    private String shopCode;

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

}
