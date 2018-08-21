/**
 * @Title XN805427Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author dl  
 * @date 2018年8月20日 下午1:20:41 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: dl 
 * @since: 2018年8月20日 下午1:20:41 
 * @history:
 */
public class XN805247Req {

    // id主键
    @NotBlank
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
