package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 修改默认邮费
 * @author: silver 
 * @since: Dec 5, 2018 2:47:53 PM 
 * @history:
 */
public class XN629790Req {

    // 编号
    @NotBlank
    private String code;

    // 价格
    @NotBlank
    private String price;

    // 更新人
    @NotBlank
    private String updater;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

}
