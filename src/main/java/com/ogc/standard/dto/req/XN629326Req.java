package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查询礼物订单
 * @author: jiafr 
 * @since: 2018年9月30日 下午2:10:58 
 * @history:
 */
public class XN629326Req {

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
