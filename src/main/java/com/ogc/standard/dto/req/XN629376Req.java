package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详细查询分享记录
 * @author: silver 
 * @since: Sep 30, 2018 11:22:10 AM 
 * @history:
 */
public class XN629376Req extends APageReq {

    private static final long serialVersionUID = 8511651091614218636L;

    // 编号
    @NotBlank
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
