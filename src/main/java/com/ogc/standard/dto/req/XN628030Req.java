package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 发帖(front)
 * @author: silver 
 * @since: 2018年8月22日 下午3:55:46 
 * @history:
 */
public class XN628030Req {
    // 内容
    @NotBlank
    private String content;

    // 发布人
    @NotBlank
    private String userId;

    // 板块编号
    @NotBlank
    private String plateCode;

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

    public String getPlateCode() {
        return plateCode;
    }

    public void setPlateCode(String plateCode) {
        this.plateCode = plateCode;
    }
}
