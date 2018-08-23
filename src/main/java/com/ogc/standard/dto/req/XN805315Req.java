/**
 * @Title XN805315Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author dl  
 * @date 2018年8月22日 下午9:20:40 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: dl 
 * @since: 2018年8月22日 下午9:20:40 
 * @history:
 */
public class XN805315Req extends APageReq {

    private static final long serialVersionUID = -2780157734357201505L;

    // id
    @NotBlank
    private String userId;

    // 接收方式
    private String receiveWay;

    // 状态
    private String status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReceiveWay() {
        return receiveWay;
    }

    public void setReceiveWay(String receiveWay) {
        this.receiveWay = receiveWay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
