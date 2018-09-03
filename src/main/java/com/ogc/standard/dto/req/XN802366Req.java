/**
 * @Title XN802366Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author dl  
 * @date 2018年8月31日 下午1:43:53 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 详情查归集订单
 * @author: dl 
 * @since: 2018年8月31日 下午1:43:53 
 * @history:
 */
public class XN802366Req {

    // 订单编号
    @NotBlank
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
