package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 删除养护记录
 * @author: silver 
 * @since: 2018年9月29日 上午10:50:15 
 * @history:
 */
public class XN629631Req extends BaseReq {

    private static final long serialVersionUID = -6308551102694787370L;

    // 养护记录编号
    @NotBlank
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
