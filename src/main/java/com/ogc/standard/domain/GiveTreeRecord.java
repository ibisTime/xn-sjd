package com.ogc.standard.domain;

import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/**
* 赠送树记录
* @author: jiafr 
* @since: 2018-09-28 14:11:40
* @history:
*/
public class GiveTreeRecord extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 认养权编号
    private String adoptTreeCode;

    // 赠送人
    private String userId;

    // 被赠送人
    private String toUserId;

    // 赠送时间
    private Date createDatetime;

    /***************DB Properties****************/
    // 赠送人
    private String userName;

    // 被赠送人
    private String toUserName;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setAdoptTreeCode(String adoptTreeCode) {
        this.adoptTreeCode = adoptTreeCode;
    }

    public String getAdoptTreeCode() {
        return adoptTreeCode;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

}
