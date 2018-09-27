package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 取消集体认养订单
 * @author: jiafr 
 * @since: 2018年9月27日 下午10:29:54 
 * @history:
 */
public class XN629052Req {

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
