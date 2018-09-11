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
 * 分页查询归集地址
 * @author: taojian 
 * @since: 2018年9月11日 下午1:59:55 
 * @history:
 */
public class XN802585Req extends APageReq {

    private static final long serialVersionUID = -4584603630956235345L;

    // 地址类型
    private String type;

    // 以太坊地址
    private String address;

    // 状态
    private String status;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
