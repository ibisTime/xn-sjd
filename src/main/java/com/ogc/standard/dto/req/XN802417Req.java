package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/** 
 * 分红明细详情查询
 * @author: lei 
 * @since: 2018年9月15日 下午5:12:50 
 * @history:
 */
public class XN802417Req {

    // 必填，序号
    @NotBlank
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
