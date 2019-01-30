/**
 * @Title XN629772Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年11月8日 上午10:39:36 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: taojian 
 * @since: 2018年11月8日 上午10:39:36 
 * @history:
 */
public class XN629774Req {

    @NotBlank
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
