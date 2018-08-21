/**
 * @Title XN805140Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author dl  
 * @date 2018年8月20日 下午5:49:15 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: dl 
 * @since: 2018年8月20日 下午5:49:15 
 * @history:
 */
public class XN805140Req {

    // 客户端
    private String client;

    // ip
    private String ip;

    // 位置
    private String location;

    // 用户编号
    @NotBlank
    private String userId;

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
