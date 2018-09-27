package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查询个人定向捐赠认养订单
 * @author: jiafr 
 * @since: 2018年9月27日 上午9:46:46 
 * @history:
 */
public class XN629046Req {

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
