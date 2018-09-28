package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 分享（认养权）
 * @author: jiafr 
 * @since: 2018年9月28日 下午1:36:42 
 * @history:
 */
public class XN629201Req {

    // 认养权编号
    @NotBlank
    private String code;

    // 分享人
    @NotBlank
    private String userId;

    // 分享渠道
    @NotBlank
    private String channel;

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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

}
