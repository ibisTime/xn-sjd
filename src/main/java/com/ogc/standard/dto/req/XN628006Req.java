package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详细查询关键字
 * @author: silver 
 * @since: 2018年8月22日 上午11:11:15 
 * @history:
 */
public class XN628006Req {

    // 编号
    @NotBlank
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
