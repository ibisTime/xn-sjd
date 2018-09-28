package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查询日志
 * @author: jiafr 
 * @since: 2018年9月28日 下午3:06:29 
 * @history:
 */
public class XN629306Req {

    @NotBlank
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
