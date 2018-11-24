package com.ogc.standard.domain;

import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 分享记录
* @author: silver 
* @since: 2018-09-29 21:28:40
* @history:
*/
public class ShareRecord extends ABaseDO {

    private static final long serialVersionUID = -2632780951096966496L;

    // 编号
    private String code;

    // 分享人
    private String userId;

    // 分享渠道(0微信/1朋友圈)
    private String channel;

    // 分享时间
    private Date createDatetime;

    /**************DB Properties**************/
    // 分享人
    private String userName;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getChannel() {
        return channel;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
