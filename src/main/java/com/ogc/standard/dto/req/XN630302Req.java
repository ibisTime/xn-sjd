/**
 * @Title XN630302Req.java 
 * @Package com.ogc.standard.dto.req 
 * @Description 
 * @author dl  
 * @date 2018年8月17日 下午6:58:38 
 * @version V1.0   
 */
package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 修改公司
 * @author: tao 
 * @since: 2018年8月17日 下午6:58:38 
 * @history:
 */
public class XN630302Req {

    // 公司编号
    @NotBlank
    private String code;

    // 公司名称
    @NotBlank
    private String name;

    // 更新人
    @NotBlank
    private String updater;

    // 备注
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

}
