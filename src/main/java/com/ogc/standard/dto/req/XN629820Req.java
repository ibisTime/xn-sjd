package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 物流轨迹
 * @author: silver 
 * @since: Nov 23, 2018 3:16:05 PM 
 * @history:
 */
public class XN629820Req {
    // 物流公司编号
    @NotBlank
    private String expCode;

    // 物流单号
    @NotBlank
    private String expNo;

    public String getExpCode() {
        return expCode;
    }

    public void setExpCode(String expCode) {
        this.expCode = expCode;
    }

    public String getExpNo() {
        return expNo;
    }

    public void setExpNo(String expNo) {
        this.expNo = expNo;
    }

}
