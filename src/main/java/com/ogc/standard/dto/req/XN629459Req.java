package com.ogc.standard.dto.req;

/**
 * 列表查询品种
 * @author: silver 
 * @since: Nov 4, 2018 3:28:56 PM 
 * @history:
 */
public class XN629459Req {

    // 状态
    private String status;

    // 类型（定向/二维码/挂单）
    private String type;

    // 最小数量
    private String minQuantity;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(String minQuantity) {
        this.minQuantity = minQuantity;
    }

}
