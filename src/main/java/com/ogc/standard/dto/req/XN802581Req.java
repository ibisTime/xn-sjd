/**
 * @Title XN625200Req.java 
 * @Package com.cdkj.coin.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年11月8日 下午3:48:07 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 弃用归集地址
 * @author: taojian 
 * @since: 2018年9月11日 下午1:59:39 
 * @history:
 */
public class XN802581Req {

    @NotBlank(message = "地址编号不能为空")
    private String id; // 地址编号

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
