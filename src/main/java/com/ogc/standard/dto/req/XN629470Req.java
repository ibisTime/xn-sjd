package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 取消订单
 * @author: silver 
 * @since: Nov 6, 2018 6:58:32 PM 
 * @history:
 */
public class XN629470Req {

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
