package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 买家取消订单
 * @author: lei 
 * @since: 2018年9月10日 下午8:07:48 
 * @history:
 */
public class XN625272Req {

    // 必填，编号
    @NotBlank
    private String code;

    // 必填,购买用户编号
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
