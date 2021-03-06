/**
 * @Title XN629726Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年11月7日 上午10:20:25 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 获取当前订单积分抵扣金额
 * @author: taojian 
 * @since: 2018年11月7日 上午10:20:25 
 * @history:
 */
public class XN629728Req {
    @NotBlank
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
