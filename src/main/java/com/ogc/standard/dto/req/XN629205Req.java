package com.ogc.standard.dto.req;

/**
 * 分页查询认养权
 * @author: jiafr 
 * @since: 2018年9月28日 下午1:56:41 
 * @history:
 */
public class XN629205Req extends APageReq {

    private static final long serialVersionUID = 8479425657381483060L;

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