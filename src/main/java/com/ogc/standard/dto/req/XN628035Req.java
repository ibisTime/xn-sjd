package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 评论帖子(front)
 * @author: silver 
 * @since: 2018年8月23日 上午10:50:03 
 * @history:
 */
public class XN628035Req {
    // 编号
    @NotBlank
    private String code;

    // 内容
    @NotBlank
    private String content;

    // 用户编号人
    @NotBlank
    private String userId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
