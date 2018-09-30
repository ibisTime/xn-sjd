package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 新增礼物订单
 * @author: jiafr 
 * @since: 2018年9月30日 上午10:51:53 
 * @history:
 */
public class XN629320Req extends BaseReq {

    private static final long serialVersionUID = 6499556783035084853L;

    // 认养权编号
    private String adoptTreeCode;

    // 礼物名称
    @NotBlank
    private String name;

    // 礼物价格
    private String price;

    // 礼物描述
    @NotBlank
    private String description;

    // 失效时间
    private String invalidDatetime;

    public String getAdoptTreeCode() {
        return adoptTreeCode;
    }

    public void setAdoptTreeCode(String adoptTreeCode) {
        this.adoptTreeCode = adoptTreeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInvalidDatetime() {
        return invalidDatetime;
    }

    public void setInvalidDatetime(String invalidDatetime) {
        this.invalidDatetime = invalidDatetime;
    }

}
