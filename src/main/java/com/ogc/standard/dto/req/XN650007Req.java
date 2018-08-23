package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 列表查询我的组合（front）
 * @author: lei 
 * @since: 2018年8月20日 下午9:25:41 
 * @history:
 */
public class XN650007Req {

    // 必填，用户编号
    @NotBlank
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
