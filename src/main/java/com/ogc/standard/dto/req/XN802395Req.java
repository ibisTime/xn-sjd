/**
 * @Title XN802395Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年9月14日 下午7:14:12 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 统计渠道商上月未结算记录分页查询
 * @author: taojian 
 * @since: 2018年9月14日 下午7:14:12 
 * @history:
 */
public class XN802395Req extends APageReq {
    private static final long serialVersionUID = -8880822311117884892L;

    // 状态
    @NotBlank
    private String status;

    // 用户编号
    private String userId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
