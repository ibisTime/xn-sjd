package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 非第一人下单集体认养订单
 * @author: jiafr 
 * @since: 2018年9月27日 下午8:37:44 
 * @history:
 */
public class XN629051Req {

    // 识别码
    @NotBlank
    private String identifyCode;

    // 下单人编号
    @NotBlank
    private String userId;

    // 下单数量
    @NotBlank
    private String quantity;

    public String getIdentifyCode() {
        return identifyCode;
    }

    public void setIdentifyCode(String identifyCode) {
        this.identifyCode = identifyCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}
