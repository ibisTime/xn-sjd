/**
 * @Title XN630301Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author dl  
 * @date 2018年8月17日 下午6:56:51 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 删除公司
 * @author: dl 
 * @since: 2018年8月17日 下午6:56:51 
 * @history:
 */
public class XN630301Req {
    // 公司编号
    @NotBlank
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
