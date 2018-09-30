package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 删除礼物订单
 * @author: jiafr 
 * @since: 2018年9月30日 上午11:15:41 
 * @history:
 */
public class XN629321Req {

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
