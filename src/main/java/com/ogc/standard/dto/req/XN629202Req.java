package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 来访人落地
 * @author: silver 
 * @since: Oct 5, 2018 4:38:18 PM 
 * @history:
 */
public class XN629202Req {

    // 认养权编号
    @NotBlank
    private String code;

    // 来访人
    @NotBlank
    private String visitorId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

}
