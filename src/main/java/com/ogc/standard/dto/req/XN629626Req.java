package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查询养护项目
 * @author: silver 
 * @since: 2018年9月29日 上午10:50:15 
 * @history:
 */
public class XN629626Req {

    // 养护项目编号
    @NotBlank
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
