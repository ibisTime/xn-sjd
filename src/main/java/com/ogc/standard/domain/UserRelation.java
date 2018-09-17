/**
 * @Title UserRelation.java 
 * @Package com.std.user.domain 
 * @Description 
 * @author xieyj  
 * @date 2016年8月31日 上午9:37:08 
 * @version V1.0   
 */
package com.ogc.standard.domain;

import java.util.Date;

import com.ogc.standard.dao.base.ABaseDO;

/** 
 * @author: xieyj 
 * @since: 2016年8月31日 上午9:37:08 
 * @history:
 */
public class UserRelation extends ABaseDO {
    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 用户编号
    private String userId;

    // 关系人编号(下家，关注人)
    private String toUser;

    // 状态(1 正常 0 假删除定时器删除 预留)
    private String status;

    // @see EUserReleationType
    private String type;

    // 创建时间
    private Date createDatetime;

    // 用户
    private User fromUserInfo;

    // 用户
    private User toUserInfo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getFromUserInfo() {
        return fromUserInfo;
    }

    public void setFromUserInfo(User fromUserInfo) {
        this.fromUserInfo = fromUserInfo;
    }

    public User getToUserInfo() {
        return toUserInfo;
    }

    public void setToUserInfo(User toUserInfo) {
        this.toUserInfo = toUserInfo;
    }

    public String getType() {
        return type;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public void setType(String type) {
        this.type = type;
    }
}
