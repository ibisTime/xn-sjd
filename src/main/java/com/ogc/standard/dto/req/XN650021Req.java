package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 提醒/取消提醒（front）
 * @author: lei 
 * @since: 2018年8月21日 上午11:23:26 
 * @history:
 */
public class XN650021Req {

    // 必填，用户编号
    @NotBlank
    private String userId;

    // 必填，组合编号
    @NotBlank
    private String groupCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

}
