package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详细查询预售订单
 * @author: silver 
 * @since: Nov 3, 2018 5:31:02 PM 
 * @history:
 */
public class XN629426Req {

    // 编号
    @NotBlank
    private String code;

    //
    private String isSettle;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIsSettle() {
        return isSettle;
    }

    public void setIsSettle(String isSettle) {
        this.isSettle = isSettle;
    }

}
