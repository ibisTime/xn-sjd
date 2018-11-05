package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 撤销寄售
 * @author: silver 
 * @since: Nov 4, 2018 2:57:58 PM 
 * @history:
 */
public class XN629440Req {

    // 编号
    @NotBlank
    private String code;

    // 用户编号
    @NotBlank
    private String userId;

    // 备注
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
