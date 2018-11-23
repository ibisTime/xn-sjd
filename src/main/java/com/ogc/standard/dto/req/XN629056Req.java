package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查询集体认养订单
 * @author: jiafr 
 * @since: 2018年9月27日 下午10:51:36 
 * @history:
 */
public class XN629056Req {

    // 订单编号
    @NotBlank
    private String code;

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
