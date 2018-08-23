package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 评论评论(front)
 * @author: silver 
 * @since: 2018年8月22日 上午11:02:12 
 * @history:
 */
public class XN628271Req {
    // 编号
    @NotBlank
    private String code;

    // 评论内容
    @NotBlank
    private String content;

    // 评论人
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
