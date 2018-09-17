package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 根据用户查认证记录
 * @author: taojian 
 * @since: 2018年9月13日 下午5:43:50 
 * @history:
 */
public class XN805167Req {
    // 申请用户
    @NotBlank
    private String applyUser;

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

}
