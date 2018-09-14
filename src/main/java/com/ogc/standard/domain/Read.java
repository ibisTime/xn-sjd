/**
 * @Title Read.java 
 * @Package com.ogc.standard.domain 
 * @Description 
 * @author dl  
 * @date 2018年8月22日 下午5:51:21 
 * @version V1.0   
 */
package com.ogc.standard.domain;

import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/** 
 * 阅读消息
 * @author: dl 
 * @since: 2018年8月22日 下午5:51:21 
 * @history:
 */
public class Read extends ABaseDO {

    private static final long serialVersionUID = -8738630231714278522L;

    // ***********db properties***********
    // id主键
    private long id;

    // 用户编号
    private String userId;

    // 消息编号
    private String smsCode;

    // 接受方式(站内消息，APP推送,短信)
    private String receiveWay;

    // 状态 0-未阅读 1-已阅读 2-已删除
    private String status;

    // 推送时间
    private Date createDatetime;

    // 阅读时间
    private Date readDatetime;

    // 删除时间
    private Date deleteDatetime;
    // ***********db properties***********

    private Sms smsInfo;

    public Sms getSmsInfo() {
        return smsInfo;
    }

    public void setSmsInfo(Sms smsInfo) {
        this.smsInfo = smsInfo;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getReadDatetime() {
        return readDatetime;
    }

    public void setReadDatetime(Date readDatetime) {
        this.readDatetime = readDatetime;
    }

    public Date getDeleteDatetime() {
        return deleteDatetime;
    }

    public void setDeleteDatetime(Date deleteDatetime) {
        this.deleteDatetime = deleteDatetime;
    }

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

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
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
