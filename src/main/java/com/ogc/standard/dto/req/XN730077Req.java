package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 根据旧密码修改密码
 * @author: silver 
 * @since: 2018年9月28日 下午3:49:20 
 * @history:
 */
public class XN730077Req extends BaseReq {

    private static final long serialVersionUID = -6308551102694787370L;

    // 用户编号
    @NotBlank
    private String userId;

    // 旧密码
    @NotBlank
    private String oldLoginPwd;

    // 新密码
    @NotBlank
    private String newLoginPwd;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOldLoginPwd() {
        return oldLoginPwd;
    }

    public void setOldLoginPwd(String oldLoginPwd) {
        this.oldLoginPwd = oldLoginPwd;
    }

    public String getNewLoginPwd() {
        return newLoginPwd;
    }

    public void setNewLoginPwd(String newLoginPwd) {
        this.newLoginPwd = newLoginPwd;
    }

}
