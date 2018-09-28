package com.ogc.standard.dto.req;

/**
 * 列表查询认养权
 * @author: silver 
 * @since: 2018年9月26日 下午5:54:37 
 * @history:
 */
public class XN629207Req extends AListReq {

    private static final long serialVersionUID = -2734869162207465263L;

    // 认养订单编号
    private String orderCode;

    // 状态(1待认养2认养中3已到期)
    private String status;

    // 当前持有人
    private String currentHolder;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentHolder() {
        return currentHolder;
    }

    public void setCurrentHolder(String currentHolder) {
        this.currentHolder = currentHolder;
    }

}
