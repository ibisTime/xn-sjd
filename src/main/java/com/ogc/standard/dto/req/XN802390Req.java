package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 渠道商佣金结算
 * @author: taojian 
 * @since: 2018年9月14日 下午7:06:34 
 * @history:
 */
public class XN802390Req {
    // 编号
    @NotBlank
    private String id;

    // 是否结算
    @NotBlank
    private String handleResult;

    // 备注
    private String handleNote;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsSettle() {
        return handleResult;
    }

    public void setIsSettle(String isSettle) {
        this.handleResult = isSettle;
    }

    public String getRemark() {
        return handleNote;
    }

    public void setRemark(String remark) {
        this.handleNote = remark;
    }
}
