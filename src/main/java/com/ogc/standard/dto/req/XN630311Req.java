/**
 * @Title XN630311Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author dl  
 * @date 2018年8月18日 上午10:09:09 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 部门删除
 * @author: tao 
 * @since: 2018年8月18日 上午10:09:09 
 * @history:
 */
public class XN630311Req {

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
