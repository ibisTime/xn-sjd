package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 取消预售订单
 * @author: silver 
 * @since: Nov 3, 2018 5:20:25 PM 
 * @history:
 */
public class XN629421Req {
    // 编号
    @NotBlank
    private String code;

    // 备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
