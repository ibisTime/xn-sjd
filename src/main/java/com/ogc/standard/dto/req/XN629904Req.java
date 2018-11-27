package com.ogc.standard.dto.req;

import java.util.List;

/**
 * 产权方已认养的总值
 * @author: silver 
 * @since: Oct 22, 2018 5:37:54 PM 
 * @history:
 */
public class XN629904Req extends BaseReq {

    private static final long serialVersionUID = 809941854249760290L;

    // 用户编号
    private String userId;

    // 状态列表
    private List<String> statusList;

    // 订单类型列表
    private List<String> orderTypeList;

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getOrderTypeList() {
        return orderTypeList;
    }

    public void setOrderTypeList(List<String> orderTypeList) {
        this.orderTypeList = orderTypeList;
    }

}
