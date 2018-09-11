package com.ogc.standard.dto.req;

import java.util.List;

/** 
 * 分页查询散取地址
 * @author: taojian 
 * @since: 2018年9月11日 上午10:36:27 
 * @history:
 */
public class XN802575Req extends APageReq {

    private static final long serialVersionUID = -4584603630956235345L;

    // 以太坊地址
    private String address;

    // 用户编号
    private String userId;

    // 状态
    private String status;

    private List<String> statusList;

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
