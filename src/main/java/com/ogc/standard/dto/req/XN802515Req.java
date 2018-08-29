/**
 * @Title XN625205Req.java 
 * @Package com.cdkj.coin.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月8日 下午3:25:36 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

/** 
 * @author: haiqingzheng 
 * @since: 2017年11月8日 下午3:25:36 
 * @history:
 */
public class XN802515Req extends APageReq {

    private static final long serialVersionUID = -4584603630956235345L;

    // 以太坊地址
    private String address;

    // 用户编号
    private String userId;

    // 状态
    private String status;

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
