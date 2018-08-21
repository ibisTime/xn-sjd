/**
 * @Title XN805205Req.java 
 * @Package com.std.user.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月23日 下午2:55:01 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月23日 下午2:55:01 
 * @history:
 */
public class XN805245Req extends APageReq {

    private static final long serialVersionUID = 3623553257483522084L;

    // 用户编号（选填）
    private String userId;

    // 拉黑类型（选填）
    private String type;

    // 状态 0-已删除 1-已生效（选填）
    private String status;

    // 更新人
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

}
