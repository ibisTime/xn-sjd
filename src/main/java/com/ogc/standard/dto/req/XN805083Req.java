/**
 * @Title XN805083Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author dl  
 * @date 2018年8月21日 下午4:04:13 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 管理员重置推荐人
 * @author: dl 
 * @since: 2018年8月21日 下午4:04:13 
 * @history:
 */
public class XN805083Req {

    // 用户id
    @NotBlank
    private String userId;

    // 推荐人id
    @NotBlank
    private String userReferee;

    // 更新人
    @NotBlank
    private String updater;

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserReferee() {
        return userReferee;
    }

    public void setUserReferee(String userReferee) {
        this.userReferee = userReferee;
    }

}
