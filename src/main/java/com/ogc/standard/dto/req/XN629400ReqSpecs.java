package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 新增预售产品规格
 * @author: silver 
 * @since: Nov 3, 2018 9:56:28 AM 
 * @history:
 */
public class XN629400ReqSpecs extends BaseReq {
    private static final long serialVersionUID = -6875342580769258538L;

    // 规格名称
    @NotBlank
    private String name;

    // 包装数量
    @NotBlank
    private String packCount;

    // 价格
    @NotBlank
    private String price;

    // 每小时涨幅
    private String increase;

    // 间隔时间
    private String intervalHours;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackCount() {
        return packCount;
    }

    public void setPackCount(String packCount) {
        this.packCount = packCount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIncrease() {
        return increase;
    }

    public void setIncrease(String increase) {
        this.increase = increase;
    }

    public String getIntervalHours() {
        return intervalHours;
    }

    public void setIntervalHours(String intervalHours) {
        this.intervalHours = intervalHours;
    }

}
