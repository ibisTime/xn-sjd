package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详细查询代理商
 * @author: silver 
 * @since: 2018年9月28日 下午4:43:59 
 * @history:
 */
public class XN730086Req extends APageReq {

    private static final long serialVersionUID = -1193785447547505011L;

    // 用户编号
    @NotBlank
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
