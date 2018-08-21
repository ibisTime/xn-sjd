/**
 * @Title XN805426Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author dl  
 * @date 2018年8月20日 下午1:22:57 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

/** 
 * @author: dl 
 * @since: 2018年8月20日 下午1:22:57 
 * @history:
 */
public class XN805246Req extends AListReq {

    private static final long serialVersionUID = -9178862532700979706L;

    // 用户编号（选填）
    private String userId;

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

    // 拉黑类型（选填）
    private String type;

    // 状态 0-已删除 1-已生效（选填）
    private String status;

    // 更新人
    private String updater;

}
