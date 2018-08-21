/**
 * @Title SignLog.java 
 * @Package com.ogc.standard.domain 
 * @Description 
 * @author dl  
 * @date 2018年8月20日 下午4:32:44 
 * @version V1.0   
 */
package com.ogc.standard.domain;

import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/** 
 * @author: dl 
 * @since: 2018年8月20日 下午4:32:44 
 * @history:
 */
public class SignLog extends ABaseDO {

    private static final long serialVersionUID = -5242661983788311175L;

    // id主键
    private long id;

    // 用户编号
    private String userId;

    // 分类（1-登录日志；2-签到日志）
    private String type;

    // ip
    private String ip;

    // 客户端
    private String client;

    // 登录时定位
    private String location;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    // 签到时间
    private Date createDatetime;
}
