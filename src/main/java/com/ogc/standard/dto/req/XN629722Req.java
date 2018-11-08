/**
 * @Title XN629721Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author taojian  
 * @date 2018年11月6日 下午3:42:36 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * @author: taojian 
 * @since: 2018年11月6日 下午3:42:36 
 * @history:
 */
public class XN629722Req {

    @NotBlank
    private String code;

    @NotBlank
    private String updater;

    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
