/**
 * @Title XN630317Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author dl  
 * @date 2018年8月18日 上午10:26:06 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 详情查询部门
 * @author: tao 
 * @since: 2018年8月18日 上午10:26:06 
 * @history:
 */
public class XN630317Req {

    // 部门编号
    @NotBlank
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
