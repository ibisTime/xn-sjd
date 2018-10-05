package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 新增古树
 * @author: silver 
 * @since: 2018年9月27日 下午8:33:25 
 * @history:
 */
public class XN629350Req {

    // 碳泡泡产生订单编号
    @NotBlank
    private String code;

    // 收取人
    @NotBlank
    private String userId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
