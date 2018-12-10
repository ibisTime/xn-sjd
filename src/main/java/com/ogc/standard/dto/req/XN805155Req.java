/**
 * @Title XN805155Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年9月14日 下午12:32:38 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

/** 
 * 分页查询用户关系
 * @author: taojian 
 * @since: 2018年9月14日 下午12:32:38 
 * @history:
 */
public class XN805155Req extends APageReq {

    private static final long serialVersionUID = 710797965742101051L;

    // 用户编号
    private String userId;

    // 对象用户编号
    private String toUser;

    // 类型 0拉黑 1信任
    private String type;

    // 好友编号
    private String friendUserId;

    // 状态
    private String status;

    private String isMySelf;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(String friendUserId) {
        this.friendUserId = friendUserId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsMySelf() {
        return isMySelf;
    }

    public void setIsMySelf(String isMySelf) {
        this.isMySelf = isMySelf;
    }

}
