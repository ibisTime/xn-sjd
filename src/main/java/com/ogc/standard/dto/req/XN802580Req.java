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
 * 导入归集地址
 * @author: taojian 
 * @since: 2018年9月11日 下午1:59:07 
 * @history:
 */
public class XN802580Req {

    // 以太坊地址
    @NotBlank(message = "归集地址不能为空")
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
