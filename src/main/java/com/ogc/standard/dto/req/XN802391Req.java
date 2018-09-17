/**
 * @Title XN802391Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年9月17日 下午4:11:28 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 申请特殊奖励
 * @author: taojian 
 * @since: 2018年9月17日 下午4:11:28 
 * @history:
 */
public class XN802391Req {
    // 申请人
    @NotBlank
    private String userId;

    // 申请数量
    @NotBlank
    private String count;

    // 申请说明
    private String remark;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
