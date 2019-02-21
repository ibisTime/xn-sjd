/**
 * @Title XN805305Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author dl  
 * @date 2018年8月22日 下午3:02:44 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

/** 
 * 分页查消息
 * @author: dl 
 * @since: 2018年8月22日 下午3:02:44 
 * @history:
 */
public class XN805305Req extends APageReq {
    private static final long serialVersionUID = 4743029699690527548L;

    // 状态
    private String status;

    // 更新人
    private String updater;

    // 类型
    private String type;

    // 标题
    private String title;

    // 对象类型
    private String object;

    // 消息用户
    private String userId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
