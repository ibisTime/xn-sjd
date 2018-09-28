package com.ogc.standard.domain;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 认养权
* @author: jiafr 
* @since: 2018-09-28 13:28:54
* @history:
*/
public class AdoptOrderTree extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 认养订单编号
    private String orderCode;

    // 古树编号
    private String treeNumber;

    // 认养开始时间
    private String startDatetime;

    // 认养结束时间
    private String endDatetime;

    // 认养金额
    private String amount;

    // 状态(1待认养2认养中3已到期)
    private String status;

    // 当前持有人
    private String currentHolder;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setTreeNumber(String treeNumber) {
        this.treeNumber = treeNumber;
    }

    public String getTreeNumber() {
        return treeNumber;
    }

    public void setStartDatetime(String startDatetime) {
        this.startDatetime = startDatetime;
    }

    public String getStartDatetime() {
        return startDatetime;
    }

    public void setEndDatetime(String endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getEndDatetime() {
        return endDatetime;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setCurrentHolder(String currentHolder) {
        this.currentHolder = currentHolder;
    }

    public String getCurrentHolder() {
        return currentHolder;
    }

}
