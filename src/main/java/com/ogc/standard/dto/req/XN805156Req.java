/**
 * @Title XN805155Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年9月14日 下午12:32:38 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 查询我和他是否建立关系
 * @author: taojian 
 * @since: 2018年9月14日 下午12:32:38 
 * @history:
 */
public class XN805156Req {

    // 用户编号
    @NotBlank
    private String userId;

    // 对象用户编号
    @NotBlank
    private String toUser;

    // 类型 0拉黑 1信任
    @NotBlank
    private String type;

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
}
