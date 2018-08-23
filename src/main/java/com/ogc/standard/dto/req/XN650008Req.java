package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 分页查询我关注的组合（front）
 * @author: lei 
 * @since: 2018年8月20日 下午9:25:30 
 * @history:
 */
public class XN650008Req extends APageReq {

    private static final long serialVersionUID = 4962150467600469430L;

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
