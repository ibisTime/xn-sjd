package com.ogc.standard.dto.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 详情查询道具
 * @author: lei 
 * @since: 2018年10月2日 下午7:53:28 
 * @history:
 */
public class XN629506Req extends BaseReq {

    private static final long serialVersionUID = -6308551102694787370L;

    // 必填，类型
    @NotBlank
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
