package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 发货
 * @author: silver 
 * @since: Nov 24, 2018 5:31:59 PM 
 * @history:
 */
public class XN629322Req extends BaseReq {

    private static final long serialVersionUID = 6499556783035084853L;

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
