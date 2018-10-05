package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 留言
 * @author: silver 
 * @since: Oct 5, 2018 1:46:26 PM 
 * @history:
 */
public class XN629204Req {

    // 认养权编号
    @NotBlank
    private String adoptTreeCode;

    // 说明
    @NotBlank
    private String note;

    // 操作人
    @NotBlank
    private String userId;

    public String getAdoptTreeCode() {
        return adoptTreeCode;
    }

    public void setAdoptTreeCode(String adoptTreeCode) {
        this.adoptTreeCode = adoptTreeCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
