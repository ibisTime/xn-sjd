package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 取消认养订单
 * @author: jiafr 
 * @since: 2018年9月27日 上午11:38:41 
 * @history:
 */
public class XN629041Req {

    // 编号
    @NotBlank
    private String code;

    @NotBlank
    private String userId;

    private String remark;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

}
