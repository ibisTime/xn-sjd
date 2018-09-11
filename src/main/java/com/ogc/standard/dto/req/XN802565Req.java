package com.ogc.standard.dto.req;

/** 
 * 分页查询分发地址
 * @author: taojian 
 * @since: 2018年9月11日 上午10:27:48 
 * @history:
 */
public class XN802565Req extends APageReq {

    private static final long serialVersionUID = -4584603630956235345L;

    // 比特币地址
    private String address;

    // 用户编号
    private String userId;

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

}
