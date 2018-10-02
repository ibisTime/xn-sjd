package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查询碳泡泡产生订单
 * @author: jiafr 
 * @since: 2018年10月2日 下午2:15:28 
 * @history:
 */
public class XN629356Req {

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
