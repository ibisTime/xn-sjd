/**
 * @Title XN802397.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年9月14日 下午8:40:29 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 单个渠道商佣金分布统计
 * @author: taojian 
 * @since: 2018年9月14日 下午8:40:29 
 * @history:
 */
public class XN802398Req {

    // 用户编号
    @NotBlank
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
